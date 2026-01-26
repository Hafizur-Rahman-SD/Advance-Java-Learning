package bd.edu.seu.shopnopuribackend.modules.Scholarship.service;


import bd.edu.seu.shopnopuribackend.modules.Scholarship.dto.*;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.Sch;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.SchApp;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.SchSave;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.repo.SchAppRepo;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.repo.SchRepo;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.repo.SchSaveRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchSvc {

    private final SchRepo schRepo;
    private final SchSaveRepo saveRepo;
    private final SchAppRepo appRepo;

    // ---------- Public ----------
    public Page<SchRes> list(String country, int page, int size) {
        Pageable pg = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Sch> data = (country == null || country.isBlank())
                ? schRepo.findByActTrue(pg)
                : schRepo.findByActTrueAndCountryIgnoreCase(country, pg);

        return data.map(this::toRes);
    }

    public SchRes get(Long id) {
        Sch s = schRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Scholarship not found"));
        return toRes(s);
    }

    // ---------- Admin ----------
    public SchRes create(SchReqC r) {
        Sch s = Sch.builder()
                .title(r.getTitle())
                .des(r.getDes())
                .lvl(r.getLvl())
                .type(r.getType())
                .country(r.getCountry())
                .provider(r.getProvider())
                .minGpa(r.getMinGpa())
                .minIncomeBdt(r.getMinIncomeBdt())
                .url(r.getUrl())
                .deadlineAt(r.getDeadlineAt())
                .act(r.getAct() == null ? true : r.getAct())
                .build();

        return toRes(schRepo.save(s));
    }

    public SchRes update(Long id, SchReqU r) {
        Sch s = schRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Scholarship not found"));

        if (r.getTitle() != null) s.setTitle(r.getTitle());
        if (r.getDes() != null) s.setDes(r.getDes());
        if (r.getLvl() != null) s.setLvl(r.getLvl());
        if (r.getType() != null) s.setType(r.getType());
        if (r.getCountry() != null) s.setCountry(r.getCountry());
        if (r.getProvider() != null) s.setProvider(r.getProvider());
        if (r.getMinGpa() != null) s.setMinGpa(r.getMinGpa());
        if (r.getMinIncomeBdt() != null) s.setMinIncomeBdt(r.getMinIncomeBdt());
        if (r.getUrl() != null) s.setUrl(r.getUrl());
        if (r.getDeadlineAt() != null) s.setDeadlineAt(r.getDeadlineAt());
        if (r.getAct() != null) s.setAct(r.getAct());

        return toRes(schRepo.save(s));
    }

    public void del(Long id) {
        if (!schRepo.existsById(id)) throw new EntityNotFoundException("Scholarship not found");
        schRepo.deleteById(id);
    }

    // ---------- Student: Save ----------
    @Transactional
    public void save(String email, Long schId) {
        saveRepo.findByStuEmailAndSch_Id(email, schId).ifPresent(x -> { throw new IllegalStateException("Already saved"); });

        Sch s = schRepo.findById(schId).orElseThrow(() -> new EntityNotFoundException("Scholarship not found"));
        saveRepo.save(SchSave.builder().stuEmail(email).sch(s).build());
    }

    @Transactional
    public void unsave(String email, Long schId) {
        long deleted = saveRepo.deleteByStuEmailAndSch_Id(email, schId);
        if (deleted == 0) throw new EntityNotFoundException("Not saved");
    }

    public List<SchRes> mySaved(String email) {
        return saveRepo.findByStuEmailOrderBySavedAtDesc(email)
                .stream().map(x -> toRes(x.getSch())).toList();
    }

    // ---------- Student: Apply ----------
    @Transactional
    public SchAppRes apply(String email, Long schId, SchAppReq r) {
        Sch s = schRepo.findById(schId).orElseThrow(() -> new EntityNotFoundException("Scholarship not found"));

        SchApp app = appRepo.findByStuEmailAndSch_Id(email, schId)
                .orElse(SchApp.builder().stuEmail(email).sch(s).build());

        app.setSt(r.getSt());
        app.setNote(r.getNote());

        SchApp saved = appRepo.save(app);

        return SchAppRes.builder()
                .schId(s.getId())
                .title(s.getTitle())
                .st(saved.getSt())
                .note(saved.getNote())
                .appliedAt(saved.getAppliedAt())
                .build();
    }

    public List<SchAppRes> myApps(String email) {
        return appRepo.findByStuEmailOrderByAppliedAtDesc(email)
                .stream()
                .map(a -> SchAppRes.builder()
                        .schId(a.getSch().getId())
                        .title(a.getSch().getTitle())
                        .st(a.getSt())
                        .note(a.getNote())
                        .appliedAt(a.getAppliedAt())
                        .build())
                .toList();
    }

    // ---------- mapper ----------
    private SchRes toRes(Sch s) {
        return SchRes.builder()
                .id(s.getId())
                .title(s.getTitle())
                .des(s.getDes())
                .lvl(s.getLvl())
                .type(s.getType())
                .country(s.getCountry())
                .provider(s.getProvider())
                .minGpa(s.getMinGpa())
                .minIncomeBdt(s.getMinIncomeBdt())
                .url(s.getUrl())
                .deadlineAt(s.getDeadlineAt())
                .act(s.isAct())
                .build();
    }
}
