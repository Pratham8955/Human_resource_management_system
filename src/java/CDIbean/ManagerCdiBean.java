/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIbean;

import EJB.ManagerSessionBeanLocal;
import Entity.Applicationsleave;
import Entity.HrmsTraingType;
import Entity.HrmsTraining;
import Entity.Hrmsemployees;
import Entity.Resignation;
import Entity.TaskSheet;
//import RestClient.ManagerRestClient;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import static java.lang.System.console;
import static java.lang.System.out;
import java.util.List;
import javax.ejb.EJB;
import java.sql.Time;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author pratham
 */
@Named(value = "managerCdiBean")
@SessionScoped
public class ManagerCdiBean implements Serializable {

//    ManagerRestClient managerRest = new ManagerRestClient();
    @EJB
    private ManagerSessionBeanLocal managerSessionBean;

    public List<Applicationsleave> ShowLeaveForManager() {
//        GenericType<List<Applicationsleave>> list1 = new GenericType<List<Applicationsleave>>() {
//        };
//        List<Applicationsleave> list = managerRest.showLeaveForManager(list1);
//        return list;
        List<Applicationsleave> list = managerSessionBean.ShowLeaveForManager();
        return list;
    }

    public void approveLeave(Applicationsleave leave) {
        if (leave != null) {
            String status = leave.getStatus();

            if (status == null) {
                leave.setStatus("Pending");
            } else if ("Pending".equals(status)) {
                leave.setStatus("Approved");
            } else if ("Approved".equals(status)) {
                leave.setStatus("Pending");
            }

            managerSessionBean.approveLeave(leave);
        }

    }

    public List<Resignation> ShowResignationForManager() {
//        GenericType<List<Resignation>> list1 = new GenericType<List<Resignation>>() {
//        };
//        List<Applicationsleave> list = managerRest.showResignationForManager(list1);
//        return list;
        List<Resignation> list = managerSessionBean.ShowResignationForManager();
        return list;
    }

    public void approveResignation(Resignation resignation) {
        if (resignation != null) {
            String status = resignation.getStatus();

            if (status == null) {
                resignation.setStatus("Pending");
            } else if ("Pending".equals(status)) {
                resignation.setStatus("Approved");
            } else if ("Approved".equals(status)) {
                resignation.setStatus("Pending");
            }
            managerSessionBean.approveResignation(resignation);
        }
    }

    public List<TaskSheet> ShowTaskForManager() {
        //        GenericType<List<TaskSheet>> list1 = new GenericType<List<TaskSheet>>() {
//        };
//        List<TaskSheet> list = managerRest.showTaskForManager(list1);
//        return list;
        List<TaskSheet> list = managerSessionBean.ShowTaskForManager();
        return list;
    }

    public void approveTask(TaskSheet task) {
        if (task != null) {
            String status = task.getStatus();

            if (status == null) {
                task.setStatus("Pending");
            } else if ("Pending".equals(status)) {
                task.setStatus("Approved");
            } else if ("Approved".equals(status)) {
                task.setStatus("Pending");
            }

            managerSessionBean.approveTask(task);
        }

    }
    Integer t_id;
    String trainer, description, status;

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String redirectAddTraining() {
        // Logic to add training
        return "AddTraining.xhtml?faces-redirect=true";
    }

    public String AddTraining() {
        HrmsTraingType hrmsTraingType = new HrmsTraingType();
        hrmsTraingType.setTrainer(trainer);
        hrmsTraingType.setDescription(description);
        hrmsTraingType.setStatus(status);
//        managerRest.addTraining(emp_status, trainer, description, status);
        managerSessionBean.AddTraining(hrmsTraingType);
        this.trainer = "";
        this.description = "";
        this.status = "";
        return "TrainingView.xhtml?faces-redirect=true";
    }

    public List<HrmsTraingType> showTrainingType() {
        //        GenericType<List<HrmsTraingType>> list1 = new GenericType<List<HrmsTraingType>>() {
//        };
//        List<HrmsTraingType> list = managerRest.showTrainingTypes(list1);
//        return list;
        List<HrmsTraingType> list = managerSessionBean.showTrainingType();
        return list;
    }

    public String deleteTrainingType(int tid) {
        System.out.println("hello");

        managerSessionBean.deleteTrainingType(tid);
        System.out.println(tid);
        return "TrainingView.xhtml?faces-redirect=true";
    }

    public String SearchTrainingType(int id) {
        try {
            HrmsTraingType train = managerSessionBean.SearchTrainingType(id);

            // Check if train is null before updating
            if (train != null) {
                this.t_id = train.getTId();
                this.trainer = train.getTrainer();
                this.description = train.getDescription();
                this.status = train.getStatus();
                return "editTrainingType.xhtml?faces-redirect=true";
            } else {
                throw new Exception("Training type not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String editTrainingType() {
        HrmsTraingType hrmsTraingType = new HrmsTraingType();
        hrmsTraingType.setTId(this.t_id); // Ensure TId is set
        hrmsTraingType.setTrainer(this.trainer);
        hrmsTraingType.setDescription(this.description);
        hrmsTraingType.setStatus(this.status);
//        managerRest.editTrainingType(hrmsTraingType);

        managerSessionBean.editTrainingType(hrmsTraingType);
        this.trainer = "";
        this.description = "";
        this.status = "";
        return "TrainingView.xhtml?faces-redirect=true";
    }

    Integer training_tid, emp_tid;
    String emp_description, emp_status, timeduration;

    public Integer getTraining_tid() {
        return training_tid;
    }

    public void setTraining_tid(Integer training_tid) {
        this.training_tid = training_tid;
    }

    public Integer getEmp_tid() {
        return emp_tid;
    }

    public void setEmp_tid(Integer emp_tid) {
        this.emp_tid = emp_tid;
    }

    public String getEmp_description() {
        return emp_description;
    }

    public void setEmp_description(String emp_description) {
        this.emp_description = emp_description;
    }

    public String getEmp_status() {
        return emp_status;
    }

    public void setEmp_status(String emp_status) {
        this.emp_status = emp_status;
    }

    public String getTimeduration() {
        return timeduration;
    }

    public void setTimeduration(String timeduration) {
        this.timeduration = timeduration;
    }

    public String AddEmpTraining() {
        HrmsTraining training = new HrmsTraining();
        HrmsTraingType train = new HrmsTraingType();
        train.setTId(training_tid);
        training.setTId(train);
        Hrmsemployees emp = new Hrmsemployees();
        emp.setUserId(emp_tid);
        training.setUserId(emp);
        training.setTimeDuration(timeduration);
        training.setDescription(emp_description);
//        managerRest.addEmpTraining(training);

        managerSessionBean.AddEmpTraining(training);
        this.training_tid = null;
        this.emp_tid = null;
        this.timeduration = "";
        this.emp_description = "";

        return "ShowTrainingsEmp.xhtml?faces-redirect=true";
    }

    public List<Hrmsemployees> getAllTrainingsEmployee() {
        //        GenericType<List<Hrmsemployees>> list1 = new GenericType<List<Hrmsemployees>>() {
//        };
//        List<Hrmsemployees> list = managerRest.getAllTrainingsEmployee(list1);
//        return list;
        List<Hrmsemployees> list = managerSessionBean.getAllTrainingsEmployee();
        return list;

    }

    public List<HrmsTraining> getTraingsSheduled() {
        //        GenericType<List<HrmsTraining>> list1 = new GenericType<List<HrmsTraining>>() {
//        };
//        List<HrmsTraining> list = managerRest.getTraingsSheduled(list1);
//        return list;
        List<HrmsTraining> list = managerSessionBean.getTraingsSheduled();
        return list;
    }

    public ManagerCdiBean() {

    }

}
