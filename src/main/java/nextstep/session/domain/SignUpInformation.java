package nextstep.session.domain;

import nextstep.session.NotProceedingException;
import nextstep.session.StudentNumberExceededException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SignUpInformation {

    private SessionStatus status;
    private Student student;

    public SignUpInformation(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, Long maxNumberOfStudent) {
        this.status = new SessionStatus(progressStatus, recruitmentStatus);
        this.student = new Student(maxNumberOfStudent);
    }

    public SignUpInformation(ProgressStatus progressStatus, Long maxNumberOfStudent) {
        this.status = new SessionStatus(progressStatus, RecruitmentStatus.RECRUITING);
        this.student = new Student(maxNumberOfStudent);
    }

    public ProgressStatus getProgressStatus() {
        return status.getProgressStatus();
    }

    public Long getMaxNumberOfStudent() {
        return student.getMaxNumberOfStudent();
    }

    public List<NsUser> getStudents() {
        return student.getStudents();
    }

    public void signUp(NsUser user) throws StudentNumberExceededException, NotProceedingException {
        status.checkSessionStatus();
        student.signUp(user);
    }
}
