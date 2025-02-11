package vn.itz.jpastudying.service.scheduler;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.repository.SubjectRegistrationRepository;
import vn.itz.jpastudying.repository.SubjectRepository;

@Service
public class SubjectScheduler {
  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  private SubjectRegistrationRepository subjectRegistrationRepository;

  // Ham kiem tra sinh vien cac khoa hoc da hoan thanh khoa hoc hay chua, neu da hoan thanh thi chuyen done
  @Scheduled(cron = "0 0 0 * * ?")
//  @Scheduled(cron = "*/10 * * * * *")
  public void updateSubjectStatus() {
    List<String> updatedSubjects = subjectRepository.findCompletedSubjects();
    int updatedRows = subjectRepository.updateCompletedSubjects();
    System.out.println("So luong khoa hoc trang thai DONE la: " + updatedRows + " khoa hoc.");
    updatedSubjects.forEach(subject -> System.out.println("Ten khoa hoc da DONE: " + subject));
  }
}
