package com.easyswitch.serbianbookers.gantogram.model;

import com.easyswitch.serbianbookers.models.Reservation;
import com.easyswitch.serbianbookers.models.Room;

import java.util.List;

/**
 * Created by Jayesh Bhadja on 5/10/17.
 */

public class GanttData {
    private static GanttData ganttData = null;
    private Project project;
    private List<Room> tasks;
    private List<Reservation> milestones;

    /**
     * Initializing Gantt Data
     *
     * @param project    Project
     * @param tasks      list of Task
     * @param milestones list of Milestones
     * @return GanttData
     */
    public static GanttData initGanttData(Project project, List<Room> tasks, List<Reservation> milestones) {
        ganttData = new GanttData();
        ganttData.setProject(project);
        ganttData.setTasks(tasks);
        ganttData.setMilestones(milestones);

        return ganttData;
    }

    public static GanttData getGanttData() {
        return ganttData;
    }

    public Project getProject() {
        return project;
    }

    /**
     * Setter for project object
     * @param project
     * Project Class Object
     */
    public void setProject(Project project) {
        this.project = project;
    }

    public List<Room> getTasks() {
        return tasks;
    }

    /**
     * Setter for Task List
     * @param tasks
     * List<Task>
     */
    public void setTasks(List<Room> tasks) {
        this.tasks = tasks;
    }

    public List<Reservation> getMilestones() {
        return milestones;
    }

    /**
     * Setter for Milestone List
     * @param milestones
     * List<Milestone>
     */
    public void setMilestones(List<Reservation> milestones) {
        this.milestones = milestones;
    }

}
