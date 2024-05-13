package ru.neoflex.practice.Contoller;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.practice.Calculations.Calculations;
import ru.neoflex.practice.Repository.CalculationsRepository;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Клиент-серверное приложение калькулятора",
                version = "1.0",
                description = "API для калькулятора"
        )
)


@RestController
@SpringBootApplication
public class CalcContoller {

    @Autowired
    private CalculationsRepository calculationsRepository;

    @GetMapping("/plus/{a}/{b}")
    public int amount(@PathVariable int a, @PathVariable int b){
        Calculations calc = new Calculations();
        calc.setNumber_1(a);
        calc.setNumber_2(b);
        calc.setSign('+');
        calc.setResult(a + b);
        calculationsRepository.save(calc);

        return calc.getResult();
    }

    @GetMapping("/minus/{a}/{b}")
    public int subtraction(@PathVariable int a, @PathVariable int b){
        Calculations calc = new Calculations();
        calc.setNumber_1(a);
        calc.setNumber_2(b);
        calc.setSign('-');
        calc.setResult(a - b);
        calculationsRepository.save(calc);

        return calc.getResult();
    }

    @GetMapping("/calculations/")
    public List<Calculations> get_calculations(){
        return calculationsRepository.findAll();
    }
}