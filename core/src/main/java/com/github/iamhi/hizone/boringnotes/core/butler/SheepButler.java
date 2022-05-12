package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import reactor.core.publisher.Flux;

public interface SheepButler {

    Flux<Void> satisfy(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO);
}
