package com.shnayder.android.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    lateinit var stopwatch: Chronometer // Хронометр
    var running = false // Хронометр работает?
    var offset: Long = 0 //Базовое смещение

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Получение ссылки на секундомер
        stopwatch = findViewById<Chronometer>(R.id.stopwatch)

        //Кнопка start запускает секундомер, если он не работал
        val startButton = findViewById<Button>(R.id.start_button)

        startButton.setOnClickListener {
            if (!running) {
                setBaseTime()
                stopwatch.start()
                running = true
            }
        }
        //Кнопка pause останавливает секундомер, если он работал
        val pauseButton = findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener {
            if (running) {
                saveOffset()
                stopwatch.stop()
                running = false
            }
        }
        //Кнопка reset обнуляет offset и базовое время
        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener {
            offset = 0
            setBaseTime()
        }
    }
    //Обновляет время stopwatch.base
    fun setBaseTime() {
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }
    //Сохраняет offset
    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }
    
}