package jp.noir.tomiyama.lavafabcustom

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    var mTimer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
    }

    private fun setupViews() {

        val handler = Handler()

        lava_fab_bottom_right.setLavaBackgroundResColor(R.color.color_purple)

        with(lava_fab_center) {
            setParentOnClickListener {
                lava_fab_center.trigger()
            }
            setChildOnClickListener(com.bitvale.lavafab.Child.TOP) {
                showToast()
                val intent = Intent(applicationContext, TestActivity::class.java)
                startActivity(intent)
                // 0.1秒遅らせる
                mTimer = Timer(false)
                mTimer?.schedule(100) {
                    handler.post {
                        lava_fab_center.collapse()
                    }
                }
            }
            setChildOnClickListener(com.bitvale.lavafab.Child.RIGHT_TOP) { showToast() }
            setChildOnClickListener(com.bitvale.lavafab.Child.RIGHT) { showToast() }
            setChildOnClickListener(com.bitvale.lavafab.Child.RIGHT_BOTTOM) { showToast() }
            setChildOnClickListener(com.bitvale.lavafab.Child.BOTTOM) { showToast() }
            setChildOnClickListener(com.bitvale.lavafab.Child.LEFT_BOTTOM) { showToast() }
            setChildOnClickListener(com.bitvale.lavafab.Child.LEFT) { showToast() }
            setChildOnClickListener(com.bitvale.lavafab.Child.LEFT_TOP) { showToast() }
        }

        with(lava_fab_top_left) {
            setParentOnClickListener { lava_fab_top_left.trigger() }
            setChildOnClickListener(com.bitvale.lavafab.Child.RIGHT) { showToast() }
            setChildOnClickListener(com.bitvale.lavafab.Child.LEFT) { showToast() }
        }

        with(lava_fab_top_right) {
            setParentOnClickListener { lava_fab_top_right.trigger() }
            setChildOnClickListener(com.bitvale.lavafab.Child.BOTTOM) { showToast() }
            setChildOnClickListener(com.bitvale.lavafab.Child.LEFT) { showToast() }
        }

        with(lava_fab_bottom_left) {
            setParentOnClickListener { lava_fab_bottom_left.trigger() }
            setChildOnClickListener(com.bitvale.lavafab.Child.TOP) { showToast() }
            setChildOnClickListener(com.bitvale.lavafab.Child.RIGHT) { showToast() }
            setChildOnClickListener(com.bitvale.lavafab.Child.LEFT) { showToast() }
        }

        with(lava_fab_bottom_right) {
            setParentOnClickListener { lava_fab_bottom_right.trigger() }
            setChildOnClickListener(com.bitvale.lavafab.Child.TOP) { showToast() }
            setChildOnClickListener(com.bitvale.lavafab.Child.LEFT) { showToast() }
            setChildOnClickListener(com.bitvale.lavafab.Child.LEFT_TOP) { showToast() }
        }

        with(lava_fab_left_center) {
            setParentOnClickListener { lava_fab_left_center.trigger() }
            setChildOnClickListener(com.bitvale.lavafab.Child.TOP) { showToast() }
            setChildOnClickListener(com.bitvale.lavafab.Child.BOTTOM) { showToast() }
        }
    }

    private fun showToast() = Toast.makeText(this, "Child clicked", Toast.LENGTH_SHORT).show()
}
