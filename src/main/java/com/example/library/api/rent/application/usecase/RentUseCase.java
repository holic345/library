package com.example.library.api.rent.application.usecase;

import com.example.library.api.rent.application.command.CreateCommand;
import com.example.library.api.rent.presentation.dto.CreateResponse;

public interface RentUseCase {

     CreateResponse createRent(CreateCommand createCommand);
}
