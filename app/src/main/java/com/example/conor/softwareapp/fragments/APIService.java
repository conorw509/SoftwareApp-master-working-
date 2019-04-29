package com.example.conor.softwareapp.fragments;

import com.example.conor.softwareapp.notifications.Sender;
import com.example.conor.softwareapp.notifications.myResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key =AAAAZB2oZlY:APA91bEKYgqFHU9g3piKjeiiq2jGC995hvizi2wiorOlnMyenR2N5pFOwnTPUW17kAB6sNlLtlj8wVBPrMxbcbq8my9TLEKIBA3zn-lNU-zCjFxkuj-L5IZU_CUnqnHaWxVIdzuLq10b"

            }
    )

    @POST("fcm/send")
    Call<myResponse> sendNotification(@Body Sender body);

}
