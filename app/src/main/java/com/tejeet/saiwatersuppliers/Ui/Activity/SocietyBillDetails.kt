package com.tejeet.saiwatersuppliers.Ui.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.itextpdf.text.*
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.pdf.draw.LineSeparator
import com.tejeet.saiwatersuppliers.R
import com.tejeet.saiwatersuppliers.databinding.ActivitySocietyBillDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class SocietyBillDetails : AppCompatActivity() {

    private  val TAG = "tag"

    lateinit var binding: ActivitySocietyBillDetailsBinding

    private var progressDialog: ProgressDialog? = null

    private var isPDFFileCreated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_society_bill_details)

        binding = ActivitySocietyBillDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        assert(
            supportActionBar != null //null check
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)



        val customerID = intent.getStringExtra("CUSTOMER_ID")
        val tankerRate = intent.getStringExtra("TANKER_RATE")
        val tankerCount = intent.getStringExtra("TANKER_COUNT")

        val totalBill = (tankerRate.toString().toInt()).times(tankerCount.toString().toInt())


        binding.tankerRate.text = "₹ ${tankerRate.toString()}"
        binding.tankerCount.text = "${tankerCount.toString()}"
        binding.billTotal.text = "₹ ${totalBill.toString()}"

        Log.d(TAG, "Data is ${intent.getStringExtra("DATA")}")


        binding.btnGeneratePdf.setOnClickListener {

            vibrateSense()
            progressDialog?.setTitle("Creating PDF Bill")
            progressDialog?.show()

            generatePDF()


        }




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }


    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@SocietyBillDetails, RevenueActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@SocietyBillDetails, RevenueActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
    }

    private fun vibrateSense() {
        val v = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            v.vibrate(50)
        }
    }

    fun generatePDF(){
        try {
            //Create file path for Pdf
            val fpath = "/sdcard//MuscleMania/Diet_plans/ABCD.pdf"
            val file = File(fpath)
            if (!file.exists()) {
                file.createNewFile()
            }


            // create an instance of itext document
            val document = Document()
            val writer = PdfWriter.getInstance(document, FileOutputStream(file.absoluteFile))
            document.open()


            // LINE SEPARATOR
            val lineSeparator = LineSeparator()
            lineSeparator.lineColor = BaseColor(0, 0, 0, 100)


            // Date
//            val c = Calendar.getInstance().time
//            val df = SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault())
//            val dataChunk = Chunk("Date : " + df.format(c), marathiB12)
//            val dateData = Paragraph(dataChunk)
//            dateData.alignment = Element.ALIGN_RIGHT


            //Header Logo Insert
            try {
                val ims = assets.open("logo.png")
                val bmp = BitmapFactory.decodeStream(ims)
                val stream = ByteArrayOutputStream()
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val image = Image.getInstance(stream.toByteArray())
                image.scaleAbsolute(540f, 70f)
                // image.scaleToFit(850,200);
                document.add(image)
            } catch (ex: IOException) {
                ex.printStackTrace()
            }

            // End Header Logo


            // Name Table
            val nameTable = PdfPTable(2) //one page contains 15 records
            nameTable.widthPercentage = 100f
            nameTable.setWidths(floatArrayOf(3f, 7f))
            nameTable.spacingBefore = 10.0f


            // BMI Content
            val bmiTable = PdfPTable(4) //one page contains 15 records
            bmiTable.widthPercentage = 100f
            bmiTable.setWidths(floatArrayOf(3f, 2f, 2f, 3f))
            bmiTable.addCell("sds")




            // Main Content
            val contentTable = PdfPTable(2) //one page contains 15 records
            contentTable.widthPercentage = 100f
            contentTable.setWidths(floatArrayOf(3f, 7f))
            contentTable.spacingBefore = 5.0f
           // contentTable.addCell"OKK")


            // Footer Content
            val supplimentTableHeader = PdfPTable(1) //one page contains 15 records
            supplimentTableHeader.widthPercentage = 100f
            supplimentTableHeader.setWidths(floatArrayOf(10f))
            supplimentTableHeader.addCell("OKd")
            val supplimentTable = PdfPTable(2) //one page contains 15 records
            supplimentTable.widthPercentage = 100f
            supplimentTable.setWidths(floatArrayOf(3f, 7f))
            supplimentTable.addCell("OK")

            // Footer Content
            val notefooterTable = PdfPTable(1) //one page contains 15 records
            notefooterTable.widthPercentage = 100f
            notefooterTable.setWidths(floatArrayOf(10f))
            notefooterTable.spacingBefore = 5.0f
            notefooterTable.addCell("OOK")
            val footerSign = Paragraph(
                "Mr. Water Suppliers\nSai Suppkier \nMob : 9763028519",)
            footerSign.alignment = Element.ALIGN_RIGHT
            document.add(Paragraph(""))
            document.add(Chunk(lineSeparator))

            document.add(nameTable)
            document.add(bmiTable)
            document.add(contentTable)
            document.add(supplimentTableHeader)
            document.add(supplimentTable)
            document.add(notefooterTable)
            document.add(footerSign)

            // close document
            document.close()
            isPDFFileCreated = true
        } catch (e: IOException) {
            e.printStackTrace()
            isPDFFileCreated = false
        } catch (e: DocumentException) {
            e.printStackTrace()
            isPDFFileCreated = false
        }
    }
}