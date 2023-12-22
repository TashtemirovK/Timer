package com.example.chronometer

import android.os.Bundle
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Определение корутины
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chronometer: Chronometer = findViewById(R.id.chronometer2)
        val btnStart: Button = findViewById(R.id.btnStart)
        val btnStop: Button = findViewById(R.id.btnStop)

        btnStart.setOnClickListener {
            // Запуск корутины при нажатии на кнопку "Start"
            job = CoroutineScope(Dispatchers.Main).launch {
                chronometer.start()
                // Выполнение асинхронной работы (здесь может быть ваш код)
            }
        }

        btnStop.setOnClickListener {
            // Остановка корутины при нажатии на кнопку "Stop"
            job?.cancel()
            chronometer.stop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Очистка ресурсов при уничтожении активности
        job?.cancel()
    }
}