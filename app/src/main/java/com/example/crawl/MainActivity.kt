package com.example.crawl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crawl.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val myRecyclerViewAdapter by lazy {
        MyListAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = myRecyclerViewAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        }

        binding.bringbtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val crawlInfo = async { crawlInfo() }
                withContext(Dispatchers.Main) {
                    myRecyclerViewAdapter.submitList(crawlInfo) //이submitList
                }

            }
        }
    }

    private suspend fun crawlInfo(): ArrayList<Data> {

        val board: ArrayList<Data> = arrayListOf()

        try {
            val doc =
                Jsoup.connect("https://www.knu.ac.kr/wbbs/wbbs/bbs/btin/list.action?bbs_cde=1&menu_idx=67")
                    .get()

            if (board.size < 3) {

                val test = doc.select(".num").text().toString()
                val test2 = doc.select(".subject").text().toString()
                val infos = Data(test, test2)

                board.add(infos)
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }


        return board

    }


}


//        binding.bringbtn.setOnClickListener {
//
//            CoroutineScope(Dispatchers.IO + job).launch {
//                val check = async { crawlInfo() }
//                val text = "${check.await()}\n"
//
//                withContext(Dispatchers.Main) {
//                    binding.tvTitle1.text = text
//                }
//
//            }
//        }
//    }
//
//    override fun onDestroy() {
//        job.cancel()
//        super.onDestroy()
//    }
//
//
