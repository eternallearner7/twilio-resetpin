package com.twilio.phonetree.servlet.reset;

import com.twilio.twiml.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PinServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws IOException {

        String selectedOption = servletRequest.getParameter("Digits");

        VoiceResponse response;
        switch (selectedOption) {
            case "1":
                response = getSuccessResponse();
                break;
            case "2":
                response = getRetryResponse();
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

    private VoiceResponse getSuccessResponse() {
        VoiceResponse response = new VoiceResponse.Builder()
                .say(new Say.Builder(
                        "Pin Reset Successful.")
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

    private VoiceResponse getRetryResponse() {
        VoiceResponse response = new VoiceResponse.Builder()
                .gather(new Gather.Builder()
                        .action("/commuter/connect")
                        .numDigits(4)
                        .build()).say(new Say.Builder(
                        "please enter a 4 digit pin")
                        .voice(Say.Voice.ALICE)
                        .language(Say.Language.EN_GB)
                        .build())
                        .build();
        return response;
    }
}

