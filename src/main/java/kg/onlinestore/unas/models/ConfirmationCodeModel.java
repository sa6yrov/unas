package kg.onlinestore.unas.models;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConfirmationCodeModel {
    Long paymentId;
    String confirmationCode;
}
