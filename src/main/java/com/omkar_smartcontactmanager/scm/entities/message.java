package com.omkar_smartcontactmanager.scm.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class message {

    private String content;
    @Builder.Default
    private messageType type = messageType.blue;
}
