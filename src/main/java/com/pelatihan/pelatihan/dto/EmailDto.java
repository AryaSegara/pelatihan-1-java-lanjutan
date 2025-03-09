package com.pelatihan.pelatihan.dto;

@Data
@Builder
@AllArgsConstructor
public class EmailDto {
    private String to;
    private String subject;
    private String body;
}
