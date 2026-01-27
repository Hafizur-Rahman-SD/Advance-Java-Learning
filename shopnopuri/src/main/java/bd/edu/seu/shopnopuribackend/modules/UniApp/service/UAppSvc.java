package bd.edu.seu.shopnopuribackend.modules.UniApp.service;

import bd.edu.seu.shopnopuribackend.modules.UniApp.dto.*;
import bd.edu.seu.shopnopuribackend.modules.UniApp.entity.UApp;
import bd.edu.seu.shopnopuribackend.modules.UniApp.entity.UAppSt;
import bd.edu.seu.shopnopuribackend.modules.UniApp.repo.UAppRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UAppSvc {

    private final UAppRepo repo;

    public List<UAppRes> myList(String email) {
        return repo.findByStuEmailOrderByUpdatedAtDesc(email)
                .stream().map(this::toRes).toList();
    }

    @Transactional
    public UAppRes create(String email, UAppReqC r) {
        ensureUniProvided(r.getUniId(), r.getUniName());

        UApp a = UApp.builder()
                .stuEmail(email)
                .uniId(r.getUniId())
                .uniName(trimToNull(r.getUniName()))
                .depId(r.getDepId())
                .depName(trimToNull(r.getDepName()))
                .year(r.getYear())
                .season(trimToNull(r.getSeason()))
                .st(r.getSt() == null ? UAppSt.PLANNING : r.getSt())
                .examDate(r.getExamDate())
                .vivaDate(r.getVivaDate())
                .note(trimToNull(r.getNote()))
                .build();

        return toRes(repo.save(a));
    }

    @Transactional
    public UAppRes update(String email, Long id, UAppReqU r) {
        UApp a = repo.findByIdAndStuEmail(id, email)
                .orElseThrow(() -> new EntityNotFoundException("Application not found"));

        if (r.getUniId() != null) a.setUniId(r.getUniId());
        if (r.getUniName() != null) a.setUniName(trimToNull(r.getUniName()));
        if (r.getDepId() != null) a.setDepId(r.getDepId());
        if (r.getDepName() != null) a.setDepName(trimToNull(r.getDepName()));
        if (r.getYear() != null) a.setYear(r.getYear());
        if (r.getSeason() != null) a.setSeason(trimToNull(r.getSeason()));
        if (r.getSt() != null) a.setSt(r.getSt());
        if (r.getExamDate() != null) a.setExamDate(r.getExamDate());
        if (r.getVivaDate() != null) a.setVivaDate(r.getVivaDate());
        if (r.getNote() != null) a.setNote(trimToNull(r.getNote()));

        // ensure still has university info
        ensureUniProvided(a.getUniId(), a.getUniName());

        return toRes(repo.save(a));
    }

    @Transactional
    public void del(String email, Long id) {
        UApp a = repo.findByIdAndStuEmail(id, email)
                .orElseThrow(() -> new EntityNotFoundException("Application not found"));
        repo.delete(a);
    }

    private UAppRes toRes(UApp a) {
        return UAppRes.builder()
                .id(a.getId())
                .uniId(a.getUniId())
                .uniName(a.getUniName())
                .depId(a.getDepId())
                .depName(a.getDepName())
                .year(a.getYear())
                .season(a.getSeason())
                .st(a.getSt())
                .examDate(a.getExamDate())
                .vivaDate(a.getVivaDate())
                .note(a.getNote())
                .createdAt(a.getCreatedAt())
                .updatedAt(a.getUpdatedAt())
                .build();
    }

    private void ensureUniProvided(Long uniId, String uniName) {
        if (uniId == null && (uniName == null || uniName.isBlank())) {
            throw new IllegalStateException("uniId or uniName required");
        }
    }

    private String trimToNull(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }
}
