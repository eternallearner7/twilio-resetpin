package com.twilio.phonetree.servlet.ivr;

import com.twilio.twiml.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws IOException {
        VoiceResponse response = new VoiceResponse.Builder()
                .gather(new Gather.Builder()
                        .action("/menu/show")
                        .numDigits(1)
                        .build()).say(new Say.Builder(
                        "Welcome to Verizon Telematics. Press 1 for RESET PIN menu. Press 2 for owner verification. To talk to a customer service representative press 0")
                        .voice(Say.Voice.ALICE)
                        .language(Say.Language.EN_GB)
                        .build())
                /*.play(new Play.Builder("http://howtodocs.s3.amazonaws.com/et-phone.mp3")
                        .loop(3)
                        .build())*/
                .build();

        servletResponse.setContentType("text/xml");
        try {
            servletResponse.getWriter().write(response.toXml());
        } catch (TwiMLException e) {
            throw new RuntimeException(e);
        }
    }
}

