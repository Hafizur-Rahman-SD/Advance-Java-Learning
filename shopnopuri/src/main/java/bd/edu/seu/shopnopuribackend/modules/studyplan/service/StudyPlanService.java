package bd.edu.seu.shopnopuribackend.modules.studyplan.service;

import bd.edu.seu.shopnopuribackend.modules.studyplan.dto.*;
import bd.edu.seu.shopnopuribackend.modules.studyplan.entity.StudyPlan;
import bd.edu.seu.shopnopuribackend.modules.studyplan.entity.StudyTask;
import bd.edu.seu.shopnopuribackend.modules.studyplan.repository.StudyPlanRepository;
import bd.edu.seu.shopnopuribackend.modules.studyplan.repository.StudyTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyPlanService {

    private final StudyPlanRepository planRepo;
    private final StudyTaskRepository taskRepo;

    // simple rule-based generator for V1
    private static final String[] DAYS = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};

    private static final String[][] SUBJECT_TOPICS = {
            {"Math", "Calculus"},
            {"Physics", "Mechanics"},
            {"Chemistry", "Organic"},
            {"English", "Grammar"},
            {"GK", "Current Affairs"},
            {"Math", "Algebra"},
            {"Physics", "Electricity"}
    };

    @Transactional
    public StudyPlanRes create(String studentEmail, StudyPlanCreateReq req) {
        StudyPlan plan = StudyPlan.builder()
                .studentEmail(studentEmail)
                .targetName(req.getTargetName())
                .weeks(req.getWeeks())
                .hoursPerDay(req.getHoursPerDay())
                .createdAt(Instant.now())
                .build();

        List<StudyTask> tasks = generateTasks(plan, req.getWeeks(), req.getHoursPerDay());
        plan.getTasks().addAll(tasks);

        StudyPlan saved = planRepo.save(plan);
        return toRes(saved);
    }

    public List<StudyPlanRes> myPlans(String studentEmail) {
        return planRepo.findByStudentEmailOrderByCreatedAtDesc(studentEmail)
                .stream().map(this::toResSummary)
                .toList();
    }

    public StudyPlanRes getOne(String studentEmail, Long id) {
        StudyPlan plan = planRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("StudyPlan not found"));

        if (!plan.getStudentEmail().equals(studentEmail)) {
            throw new IllegalArgumentException("Not your plan");
        }
        return toRes(plan);
    }

    @Transactional
    public StudyPlanRes markComplete(String studentEmail, Long planId, Long taskId) {
        StudyPlan plan = planRepo.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("StudyPlan not found"));

        if (!plan.getStudentEmail().equals(studentEmail)) {
            throw new IllegalArgumentException("Not your plan");
        }

        StudyTask task = taskRepo.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (!task.getPlan().getId().equals(planId)) {
            throw new IllegalArgumentException("Task does not belong to plan");
        }

        task.setCompleted(true);
        taskRepo.save(task);

        return toRes(planRepo.findById(planId).orElseThrow());
    }

    @Transactional
    public StudyPlanRes regenerate(String studentEmail, Long planId) {
        StudyPlan plan = planRepo.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("StudyPlan not found"));

        if (!plan.getStudentEmail().equals(studentEmail)) {
            throw new IllegalArgumentException("Not your plan");
        }

        // remove old tasks and regenerate
        plan.getTasks().clear();
        List<StudyTask> tasks = generateTasks(plan, plan.getWeeks(), plan.getHoursPerDay());
        plan.getTasks().addAll(tasks);

        StudyPlan saved = planRepo.save(plan);
        return toRes(saved);
    }

    private List<StudyTask> generateTasks(StudyPlan plan, int weeks, int hoursPerDay) {
        int minutesPerDay = hoursPerDay * 60;
        int focusMinutes = Math.max(30, minutesPerDay / 2);

        List<StudyTask> out = new ArrayList<>();
        int idx = 0;

        for (int w = 1; w <= weeks; w++) {
            for (int d = 0; d < DAYS.length; d++) {
                String[] pick = SUBJECT_TOPICS[idx % SUBJECT_TOPICS.length];
                idx++;

                out.add(StudyTask.builder()
                        .plan(plan)
                        .weekNo(w)
                        .dayName(DAYS[d])
                        .subject(pick[0])
                        .topic(pick[1])
                        .minutes(focusMinutes)
                        .completed(false)
                        .build());
            }
        }
        return out;
    }

    private StudyPlanRes toRes(StudyPlan plan) {
        List<StudyTaskRes> tasks = plan.getTasks().stream()
                .map(t -> StudyTaskRes.builder()
                        .id(t.getId())
                        .weekNo(t.getWeekNo())
                        .dayName(t.getDayName())
                        .subject(t.getSubject())
                        .topic(t.getTopic())
                        .minutes(t.getMinutes())
                        .completed(t.getCompleted())
                        .build())
                .toList();

        return StudyPlanRes.builder()
                .id(plan.getId())
                .studentEmail(plan.getStudentEmail())
                .targetName(plan.getTargetName())
                .weeks(plan.getWeeks())
                .hoursPerDay(plan.getHoursPerDay())
                .createdAt(plan.getCreatedAt())
                .completionPercent(calcCompletion(tasks))
                .tasks(tasks)
                .build();
    }

    // summary response for list page
    private StudyPlanRes toResSummary(StudyPlan plan) {
        var tasks = plan.getTasks();
        int total = tasks.size();
        int done = (int) tasks.stream().filter(t -> Boolean.TRUE.equals(t.getCompleted())).count();
        int percent = total == 0 ? 0 : (done * 100) / total;

        return StudyPlanRes.builder()
                .id(plan.getId())
                .studentEmail(plan.getStudentEmail())
                .targetName(plan.getTargetName())
                .weeks(plan.getWeeks())
                .hoursPerDay(plan.getHoursPerDay())
                .createdAt(plan.getCreatedAt())
                .completionPercent(percent)
                .tasks(null)
                .build();
    }

    private int calcCompletion(List<StudyTaskRes> tasks) {
        if (tasks == null || tasks.isEmpty()) return 0;
        long done = tasks.stream().filter(t -> Boolean.TRUE.equals(t.getCompleted())).count();
        return (int) ((done * 100) / tasks.size());
    }
}
