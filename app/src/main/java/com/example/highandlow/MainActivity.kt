package com.example.highandlow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.highandlow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tag = "high and low"
    //自分のカードの目
    private var yourCard = 0
    //相手のカードの目
    private var droidCard = 0
    //あたりの数
    private var hitCount = 0
    //外れの数
    private var loseCount = 0
    //ゲーム開始かどうか
    private var gameStart = false
    //出された問題に答えたかどうか
    private var answered = false

    //アクティビティ生成時に呼び出される。アクティビティの初期化を行う
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //setOnClickListenerはonCreateメソッドで記述する
        binding.highBtn.setOnClickListener {
            //ゲーム開始状態かつ未回答の場合
            if(gameStart && !answered){
                highAndLow('h')
            }
        }
        binding.lowBtn.setOnClickListener {
            if(gameStart && !answered){
                highAndLow('l')
            }
        }
        binding.nextBtn.setOnClickListener {
            if(gameStart){
                drawCard()
            }
        }
    }

    //アクティビティが表示状態の時に呼び出される。onCrate()と違い複数回呼び出されることがある
    override fun onResume() {
        super.onResume()
        hitCount = 0
        loseCount = 0
        binding.hitText.text = getString(R.string.hit_text)
        binding.loseText.text = getString(R.string.lose_text)
        gameStart = true
        drawCard()
    }

    private fun drawCard(){
        binding.yourCardImage.setImageResource(R.drawable.z02)
        binding.droidCardImage.setImageResource(R.drawable.z01)

        //IntRange.random()で乱数を生成
        yourCard = (1..13).random()
        //d:デバッグ情報を出力するレベル
        Log.d(tag,"You:"+yourCard)

        when(yourCard){
            1 -> binding.yourCardImage.setImageResource(R.drawable.d01)
            2 -> binding.yourCardImage.setImageResource(R.drawable.d02)
            3 -> binding.yourCardImage.setImageResource(R.drawable.d03)
            4 -> binding.yourCardImage.setImageResource(R.drawable.d04)
            5 -> binding.yourCardImage.setImageResource(R.drawable.d05)
            6 -> binding.yourCardImage.setImageResource(R.drawable.d06)
            7 -> binding.yourCardImage.setImageResource(R.drawable.d07)
            8 -> binding.yourCardImage.setImageResource(R.drawable.d08)
            9 -> binding.yourCardImage.setImageResource(R.drawable.d09)
            10 -> binding.yourCardImage.setImageResource(R.drawable.d10)
            11 -> binding.yourCardImage.setImageResource(R.drawable.d11)
            12 -> binding.yourCardImage.setImageResource(R.drawable.d12)
            13 -> binding.yourCardImage.setImageResource(R.drawable.d13)
        }
        droidCard = (1..13).random()
        Log.d(tag,"droid:"+droidCard)
        answered = false
    }

    private fun highAndLow(answer:Char){
        //CPU側のカードを表示する
        showDroidCard()
        answered = true
        //CPU側のカード-自分のカード
        val balance = droidCard - yourCard

        if(balance == 0){
            //do nothing
        }else if(balance < 0 && answer == 'h'){
            hitCount++
            binding.hitText.text = getString(R.string.hit_text) + hitCount
        }else if(balance > 0 && answer == 'l'){
            hitCount++
            binding.hitText.text = getString(R.string.hit_text) + hitCount
        }else{
            loseCount++
            binding.loseText.text = getString(R.string.lose_text) + loseCount
        }

        if (hitCount == 5){
            binding.resultText.text = "あなたの勝ちです"
            gameStart = false
        }else if(loseCount == 5){
            binding.resultText.text = "あなたの負けです"
            gameStart = false
        }else{
            // do nothing
        }
    }

    //CPU側のカードを表向きにする処理
    private fun showDroidCard(){
        when(droidCard){
            1 -> binding.droidCardImage.setImageResource(R.drawable.s01)
            2 -> binding.droidCardImage.setImageResource(R.drawable.s02)
            3 -> binding.droidCardImage.setImageResource(R.drawable.s03)
            4 -> binding.droidCardImage.setImageResource(R.drawable.s04)
            5 -> binding.droidCardImage.setImageResource(R.drawable.s05)
            6 -> binding.droidCardImage.setImageResource(R.drawable.s06)
            7 -> binding.droidCardImage.setImageResource(R.drawable.s07)
            8 -> binding.droidCardImage.setImageResource(R.drawable.s08)
            9 -> binding.droidCardImage.setImageResource(R.drawable.s09)
            10 -> binding.droidCardImage.setImageResource(R.drawable.s10)
            11 -> binding.droidCardImage.setImageResource(R.drawable.s11)
            12 -> binding.droidCardImage.setImageResource(R.drawable.s12)
            13 -> binding.droidCardImage.setImageResource(R.drawable.s13)
        }
    }
}