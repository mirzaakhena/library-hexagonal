package io.wkrzywiec.hexagonal.library.email;

import io.wkrzywiec.hexagonal.library.email.model.Email;
import io.wkrzywiec.hexagonal.library.email.ports.outgoing.EmailSender;

public class EmailSenderFake implements EmailSender {

    @Override
    public void sendReservationConfirmationEmail(Email email) {

    }
}
