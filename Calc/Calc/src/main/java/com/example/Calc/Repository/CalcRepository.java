package com.example.Calc.Repository;

import com.example.Calc.domain.CalcStatus;
import com.example.Calc.domain.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalcRepository {
    private final EntityManager em;

    // 저장
    @Transactional
    public void save(Calculator calculator) {
        em.persist(calculator);
    }

    // 불러오기
    public List<Calculator> findAll() {
        return em.createQuery("select c from Calculator c", Calculator.class)
                .getResultList();
    }

    public List<Calculator> find(Long id) {
        return em.createQuery("select c from Calculator c where c.id = :id", Calculator.class)
                .setParameter("id", id)
                .getResultList();
    }


    // 삭제하기
    public void remove(Long id) {
        List<Calculator> result = find(id);
        for (Calculator calculator : result) {
            calculator.setStatus(CalcStatus.DELETE);
        }

    }

}
