package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Dates (@SerializedName("maximum") @Expose var maximum : String,
             @SerializedName("minimum") @Expose var minimum : String)