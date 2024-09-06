package com.example.library.api.rent.application.usecase;

import com.example.library.api.rent.application.command.CreateCommand;

public interface RentUseCase {

     Long createRent(CreateCommand createCommand);
}
