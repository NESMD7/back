package com.example.enetrevfinal.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFile {
    private String name;
    private String url;
    private long size;
    private Integer IdMatiere;
}
