package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
record SheepButlerImpl(
    List<SheepServingDish> sheepServingDishes
) implements SheepButler {

    @Override
    public Flux<Void> satisfy(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO) {
        List<SheepServingDish> sheepDishes = sheepServingDishes.stream()
            .filter(dish -> dish.canServe(userInputDataDTO.getCommand())).toList();

        return Flux.fromIterable(sheepDishes).flatMapSequential(dish -> dish.serve(userInputDataDTO, sheepDTO));
    }
}
