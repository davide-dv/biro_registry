package it.biro.biro_registry;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LogData {
    private Date date;
    private String origin;
    private String message;
}
