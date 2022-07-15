package com.example.Calc.Service;

import com.example.Calc.Repository.CalcRepository;
import com.example.Calc.domain.CalcStatus;
import com.example.Calc.domain.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CalcService {
    private final CalcRepository calcRepository;

    public void saveData(Calculator calculator) {
        calcRepository.save(calculator);
    }

    public List<Calculator> showResult() {
        return calcRepository.findAllByStatus();
    }

    public void deleteCalc(Long id) {
        Calculator calculator = calcRepository.findOne(id);
        calculator.setStatus(CalcStatus.DELETE);
    }

}
