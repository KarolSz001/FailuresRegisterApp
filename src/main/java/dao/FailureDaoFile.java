package dao;

import enums.Area;
import enums.Priority;
import exception.MyUncheckedException;
import model.Failure;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FailureDaoFile implements FailureDao {

    private final static String FILE_NAME = "dataFailure.txt";

    public FailureDaoFile() {
        File file = new File(FILE_NAME);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyUncheckedException("CAN'T CREATE FILE");
        }
    }

    private static void clearFile() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(FILE_NAME);
        printWriter.close();
    }

    private void save(Collection<Failure> failures) {
        try {
            clearFile();
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(FILE_NAME, true));

            for (Failure failure : failures) {
                printWriter.write(failure.toString() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Failure save(Failure failure) {
        return null;
    }

    @Override
    public Collection<Failure> getAllFailures() {
        List<Failure> failures = new LinkedList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
            String readLine = bufferedReader.readLine();

            while (readLine != null) {
                String[] values = readLine.split("|");
                Failure failure = new Failure(Long.parseLong(values[0]), values[1], Priority.valueOf(values[2]), Area.valueOf(values[3]), values[4]);
                failures.add(failure);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return failures;
    }

    @Override
    public Optional<Failure> getFailureById(Long id) {
        return getAllFailures().stream().filter(failure -> failure.getId().equals(id)).findFirst();
    }

    @Override
    public Collection<Failure> getFailureByAres(Area area) {
        return getAllFailures().stream().filter(failure -> failure.getArea().equals(area)).collect(Collectors.toList());
    }

    @Override
    public Collection<Failure> getFailureByPriority(Priority priority) {
        return getAllFailures().stream().filter(failure -> failure.getArea().equals(priority)).collect(Collectors.toList());

    }
    @Override
    public void deleteFailureById(Long id) {
        List<Failure> failures = (ArrayList)getAllFailures();
        failures.removeIf(failure -> failure.getId().equals(id));
        save(failures);
    }

    @Override
    public void deleteFailureByOwner(String owner) {
        List<Failure> failures = (ArrayList)getAllFailures();
        failures.removeIf(failure -> failure.getOwner().equals(owner));
        save(failures);
    }

    @Override
    public Failure update(Failure failure) {
        deleteFailureById(failure.getId());
        List<Failure> failures = new LinkedList<>();
        failures.add(failure);
        save(failure);
        return failure;
    }
}
