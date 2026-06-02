package com.fusis.original.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

@Service
public class GoogleCalendarService {

    public String createMeetEvent(String accessToken, String summary,
                                   String studentEmail, String teacherEmail,
                                   LocalDateTime dateTime) {
        try {
            // Token ile credentials oluştur
            GoogleCredentials credentials = GoogleCredentials.create(
                new AccessToken(accessToken, null)
            );

            Calendar service = new Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials)
            ).setApplicationName("FUSIS").build();

            // Etkinlik oluştur
            Event event = new Event()
                .setSummary(summary);

            // Başlangıç zamanı
            Date startDate = Date.from(
                dateTime.atZone(ZoneId.systemDefault()).toInstant()
            );
            EventDateTime start = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(startDate))
                .setTimeZone("Europe/Istanbul");
            event.setStart(start);

            // Bitiş zamanı (1 saat sonra)
            Date endDate = Date.from(
                dateTime.plusHours(1).atZone(ZoneId.systemDefault()).toInstant()
            );
            EventDateTime end = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(endDate))
                .setTimeZone("Europe/Istanbul");
            event.setEnd(end);

            // Katılımcılar
            event.setAttendees(Arrays.asList(
                new EventAttendee().setEmail(studentEmail),
                new EventAttendee().setEmail(teacherEmail)
            ));

            // Meet linki oluştur
            ConferenceData conferenceData = new ConferenceData();
            CreateConferenceRequest createRequest = new CreateConferenceRequest();
            createRequest.setRequestId("fusis-" + System.currentTimeMillis());
            conferenceData.setCreateRequest(createRequest);
            event.setConferenceData(conferenceData);

            // Takvime ekle
            Event createdEvent = service.events()
                .insert("primary", event)
                .setConferenceDataVersion(1)
                .execute();

            // Meet linkini döndür
            return createdEvent.getHangoutLink();

        } catch (Exception e) {
            throw new RuntimeException("Google Meet etkinliği oluşturulamadı: " + e.getMessage());
        }
    }
}