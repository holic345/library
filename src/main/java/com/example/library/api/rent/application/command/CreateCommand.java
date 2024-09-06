package com.example.library.api.rent.application.command;

public record CreateCommand(

    Long userKey,

    Long bookKey

) {}
