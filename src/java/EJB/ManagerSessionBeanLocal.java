/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJB;

import Entity.Applicationsleave;
import Entity.HrmsTraingType;
import Entity.HrmsTraining;
import Entity.Hrmsemployees;
import Entity.Resignation;
import Entity.TaskSheet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pratham
 */
@Local
public interface ManagerSessionBeanLocal {

    public List<Applicationsleave> ShowLeaveForManager();

    public void approveLeave(Applicationsleave leave);

    public List<Resignation> ShowResignationForManager();

    public void approveResignation(Resignation resignation);

    public List<TaskSheet> ShowTaskForManager();

    public void approveTask(TaskSheet task);

    public void AddTraining(HrmsTraingType train);

    public List<HrmsTraingType> showTrainingType();

    public String deleteTrainingType(int tid);

    public HrmsTraingType SearchTrainingType(int id);

    public String editTrainingType(HrmsTraingType train);

    public void AddEmpTraining(HrmsTraining training);

    public List<Hrmsemployees> getAllTrainingsEmployee();

    public List<HrmsTraining> getTraingsSheduled();

}
