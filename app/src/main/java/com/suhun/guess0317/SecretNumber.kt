package com.suhun.guess0317

import android.content.res.Resources
import android.util.Log
import java.util.Random

class SecretNumber {
    var tag = SecretNumber::class.java.simpleName
    var randomNumber:Int
    var guessCount:Int = 0
    init{
        randomNumber = Random().nextInt(100) + 1
        Log.d(tag, "Secret number is $randomNumber")
    }

    fun verify(userInput:Int) = randomNumber - userInput

    fun verifyResult(r:Resources, userInput:Int):String{
        guessCount++
        if(verify(userInput)>0){
            return "Bigger!!!"
        }else if(verify(userInput)<0){
            return "Smaller!!!"
        }else{
            if(guessCount < 3){
                return "Excellent! The number is $randomNumber"
            }else{
                return "You got it!!!"
            }
        }
    }

    fun resetAll(){
        guessCount = 0
        randomNumber = Random().nextInt(100) + 1
        Log.d(tag, "Reset secret number is $randomNumber")
    }
}