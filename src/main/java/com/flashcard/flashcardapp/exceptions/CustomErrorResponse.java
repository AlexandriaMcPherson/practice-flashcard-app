package com.flashcard.flashcardapp.exceptions;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private int statusCode;
    private String statusName;
    private String message;
    private Object data;
}
