package model;

import enums.Area;
import enums.Priority;

import java.math.BigDecimal;
import java.util.Objects;

public class Failure {

    private Integer id;
    private String description;
    private Priority priority;
    private Area area;
    private String owner;

    public Failure(Integer id, String description, Priority priority, Area area, String owner) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.area = area;
        this.owner = owner;
    }



    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Failure{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", area=" + area +
                ", owner='" + owner + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Failure failure = (Failure) o;
        return Objects.equals(id, failure.id) &&
                Objects.equals(description, failure.description) &&
                priority == failure.priority &&
                area == failure.area &&
                Objects.equals(owner, failure.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, priority, area, owner);
    }
}
