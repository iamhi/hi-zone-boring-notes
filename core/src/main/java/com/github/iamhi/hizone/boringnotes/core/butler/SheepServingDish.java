package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import reactor.core.publisher.Flux;

interface SheepServingDish {

    boolean canServe(String userCommand);

    Flux<Void> serve(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO);
}
