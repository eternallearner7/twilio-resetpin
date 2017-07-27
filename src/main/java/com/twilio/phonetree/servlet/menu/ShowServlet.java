package com.twilio.phonetree.servlet.menu;


import com.twilio.twiml.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws IOException {

        String selectedOption = servletRequest.getParameter("Digits");

        VoiceResponse response;
        switch (selectedOption) {
            case "1":
                response = initiateResetPin();
                break;
            case "2":
                response = initiateOwnerVerification();
                break;
            default:
                response = com.twilio.phonetree.servlet.common.Redirect.toMainMenu();
        }

        servletResponse.setContentType("text/xml");
        try {
            servletResponse.getWriter().write(response.toXml());
        } catch (TwiMLException e) {
            throw new RuntimeException(e);
        }
    }

    private VoiceResponse initiateResetPin() {
        VoiceResponse response = new VoiceResponse.Builder()
                .gather(new Gather.Builder()
                        .action("/commuter/connect")
                        .numDigits(4)
                        .build()).say(new Say.Builder(
                        "please enter a 4 digit pin")
                        .voice(Say.Voice.ALICE)
                        .language(Say.Language.EN_GB)
                        .build())
                /*.play(new Play.Builder("http://howtodocs.s3.amazonaws.com/et-phone.mp3")
                        .loop(3)
                        .build())*/
                .build();

      /*  VoiceResponse response = new VoiceResponse.Builder()
                .say(new Say.Builder(
                        "Enter 4 digits for new pin")
                        .voice(Say.Voice.ALICE)
                        .language(Say.Language.EN_GB)
                        .build())
                .say(new Say.Builder(
                        "Thank you for calling the ET Phone Home Service - the "
                        + "adventurous alien's first choice in intergalactic travel")
                        .build())
                .hangup(new Hangup())
                .build();*/

        return response;
    }

    private VoiceResponse initiateOwnerVerification() {

        /*VoiceResponse response = new VoiceResponse.Builder()
                .gather(new Gather.Builder()
                        .action("/commuter/connect")
                        .numDigits(1)
                        .build())
                .say(new Say.Builder(
                        "To call the planet Broh doe As O G, press 2. To call the planet "
                        + "DuhGo bah, press 3. To call an oober asteroid to your location"
                        + ", press 4. To go back to the main menu, press the star key ")
                        .voice(Say.Voice.ALICE)
                        .language(Say.Language.EN_GB)
                        .loop(3)
                        .build()
                ).build();*/
        VoiceResponse response = new VoiceResponse.Builder()
                .say(new Say.Builder(
                        "owner Verification successful")
                        .voice(Say.Voice.ALICE)
                        .language(Say.Language.EN_GB)
                        .build())
                .say(new Say.Builder(
                        "Thank you for calling. goodbye. ")
                        .build())
                .hangup(new Hangup())
                .build();

        return response;
    }
}

