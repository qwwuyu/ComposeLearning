package com.qwwuyu.base.utils

import java.io.*
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

actual object WLog {
    private const val V = 2
    private const val D = 3
    private const val I = 4
    private const val W = 5
    private const val E = 6
    private const val A = 7
    private val T = charArrayOf('V', 'D', 'I', 'W', 'E', 'A')
    private const val JSON = 0x10
    private const val XML = 0x20

    private val FORMAT: Format = SimpleDateFormat("MM-dd HH:mm:ss.SSS ")
    private val LINE_SEP = System.getProperty("line.separator") ?: "\n"
    private const val TOP_BORDER =
        "╔═══════════════════════════════════════════════════════════════════════════════════════════════════"
    private const val LEFT_BORDER = "║ "
    private const val BOTTOM_BORDER =
        "╚═══════════════════════════════════════════════════════════════════════════════════════════════════"
    private const val MAX_LEN = 4000
    private const val NULL = "null"
    private var LOG = false
    private var logTag: String? = null
    private var logHead = false
    private var logBorder = false
    private var logFilter = V
    private var executor: ExecutorService? = null
    private var dir: String? = null
    private var head_sep = LINE_SEP

    actual fun v(contents: Any) {
        log4(V, logTag, contents)
    }

    actual fun d(contents: Any) {
        log4(D, logTag, contents)
    }

    actual fun i(contents: Any) {
        log4(I, logTag, contents)
    }

    actual fun w(contents: Any) {
        log4(W, logTag, contents)
    }

    actual fun e(contents: Any) {
        log4(E, logTag, contents)
    }

    actual fun a(contents: Any) {
        log4(A, logTag, contents)
    }

    actual fun v(tag: String?, format: String, vararg args: Any?) {
        log4(V, tag, format, *args)
    }

    actual fun d(tag: String?, format: String, vararg args: Any?) {
        log4(D, tag, format, *args)
    }

    actual fun i(tag: String?, format: String, vararg args: Any?) {
        log4(I, tag, format, *args)
    }

    actual fun w(tag: String?, format: String, vararg args: Any?) {
        log4(W, tag, format, *args)
    }

    actual fun e(tag: String?, format: String, vararg args: Any?) {
        log4(E, tag, format, *args)
    }

    actual fun a(tag: String?, format: String, vararg args: Any?) {
        log4(A, tag, format, *args)
    }

    actual fun json(@TYPE type: Int, tag: String?, contents: String) {
        log4(JSON or type, tag, contents)
    }

    actual fun xml(@TYPE type: Int, tag: String?, contents: String) {
        log4(XML or type, tag, contents)
    }

    actual fun onError(e: Throwable?, flag: Int) {
        if (LOG && logFilter < E && e != null) {
            e.printStackTrace()
        }
    }

    actual fun printStackTrace(e: Throwable?) {
        if (LOG && logFilter < E && e != null) {
            e.printStackTrace()
        }
    }

    actual fun logError(e: Throwable?) {
        if (LOG && logFilter < E && e != null) {
            log(E, logHead, null, 3, e.stackTraceToString())
            e.printStackTrace()
        }
    }

    /** 处理日志  */
    private fun log4(type: Int, tag: String?, format: Any, vararg args: Any?) {
        log(type, logHead, tag, 4, format, *args)
    }

    /** 处理日志  */
    fun log(type: Int, logHead: Boolean, tag: String?, stackTrace: Int, format: Any?, vararg args: Any?) {
        if (!LOG) return
        val typeLow = type and 0x0f
        val typeHigh = type and 0xf0
        if (typeLow < logFilter) return
        val tagAndHead = processTagAndHead(logHead, tag, stackTrace)
        val body = processBody(typeHigh, format, *args)
        print2Console(typeLow, tagAndHead[0], tagAndHead[1].toString() + body)
        if (dir != null) print2File(
            typeLow,
            tagAndHead[0], tagAndHead[2].toString() + body
        )
    }

    /** 处理TAG和位子  */
    private fun processTagAndHead(logHead: Boolean, _tag: String?, stackTrace: Int): Array<String?> {
        var tag = _tag
        if (tag == null) tag = logTag
        if (logHead || tag == null) {
            val targetElement = Throwable().stackTrace[stackTrace]
            var className = targetElement.className
            val classNameInfo = className.split("\\.").toTypedArray()
            if (classNameInfo.isNotEmpty()) {
                className = classNameInfo[classNameInfo.size - 1]
            }
            if (className.contains("$")) {
                className = className.split("\\$").toTypedArray()[0]
            }
            if (tag == null) tag = className
            var fileName = targetElement.fileName
            if (fileName == null) fileName = "$className.java"
            if (logHead) {
                val head = String.format(
                    Locale.getDefault(), "%s, %s(%s:%d)",
                    Thread.currentThread().name, targetElement.methodName, fileName, targetElement.lineNumber
                )
                return arrayOf(tag, head + head_sep, " [$head]: ")
            }
        }
        return arrayOf(tag, "", ": ")
    }

    /** 处理内容  */
    private fun processBody(type: Int, format: Any?, vararg args: Any?): String {
        if (format == null) {
            return NULL
        }
        val body = format.toString()
        return if (type == JSON) {
            body
        } else if (type == XML) {
            formatXml(body)
        } else if (args.isEmpty()) {
            body
        } else {
            String.format(body, *args)
        }
    }

    /** 打印日志到控制台  */
    private fun print2Console(type: Int, tag: String?, _msg: String) {
        var msg = _msg
        if (logBorder) print(type, tag, TOP_BORDER)
        if (logBorder) msg = addLeftBorder(msg)
        val len = msg.length
        val countOfSub = len / MAX_LEN
        if (countOfSub > 0) {
            print(type, tag, msg.substring(0, MAX_LEN))
            var sub: String
            var index = MAX_LEN
            for (i in 1 until countOfSub) {
                sub = msg.substring(index, index + MAX_LEN)
                print(type, tag, if (logBorder) LEFT_BORDER + sub else sub)
                index += MAX_LEN
            }
            sub = msg.substring(index, len)
            print(type, tag, if (logBorder) LEFT_BORDER + sub else sub)
        } else {
            print(type, tag, msg)
        }
        if (logBorder) print(type, tag, BOTTOM_BORDER)
    }

    private fun print(type: Int, tag: String?, msg: String) {
        println("${T[type - V]}/${tag}: $msg")
    }

    /** 打印日志到文件  */
    private fun print2File(type: Int, tag: String?, msg: String) {
        val format = FORMAT.format(Date(System.currentTimeMillis()))
        val date = format.substring(0, 5)
        val time = format.substring(6)
        val fullPath = dir + File.separator + date + ".txt"
        if (!File(fullPath).exists()) {
            try {
                clearHistoryLog()
            } catch (ignored: Exception) {
            }
        }
        if (!createOrExistsFile(fullPath)) {
            return
        }
        val content = time + T[type - V] + "/" + tag + msg + LINE_SEP
        if (executor == null) {
            synchronized(WLog::class.java) {
                if (executor == null) {
                    executor = Executors.newSingleThreadExecutor()
                }
            }
        }
        executor!!.execute {
            try {
                BufferedWriter(FileWriter(fullPath, true)).use { bw -> bw.write(content) }
            } catch (ignored: IOException) {
            }
        }
    }

    /** 清除月份相差两个月的日志文件 3月清除非2、3月  */
    private fun clearHistoryLog() {
        val dir = dir ?: return
        val logDir = File(dir)
        if (!logDir.isDirectory) return
        val files = logDir.listFiles() ?: return
        val calendar = Calendar.getInstance()
        val nowMonth = calendar[Calendar.MONTH] + 1
        for (file in files) {
            val fileName = file.name
            if (file.isFile && fileName.endsWith(".txt")) {
                if (fileName.matches("^\\d{2}-\\d{2}\\.txt$".toRegex())) {
                    var month = fileName.substring(0, 2).toInt()
                    if (month > nowMonth) month -= 12
                    if (month >= nowMonth - 1) {
                        continue
                    }
                }
                file.delete()
            }
        }
    }

    private fun createOrExistsFile(filePath: String): Boolean {
        return try {
            val file = File(filePath)
            if (file.exists()) file.isFile else createOrExistsDir(file.parentFile) && file.createNewFile()
        } catch (e: Exception) {
            false
        }
    }

    private fun createOrExistsDir(file: File?): Boolean {
        return file != null && if (file.exists()) file.isDirectory else file.mkdirs()
    }

    private fun formatXml(xml: String): String {
        try {
            val xmlInput: Source = StreamSource(StringReader(xml))
            val xmlOutput = StreamResult(StringWriter())
            val transformer = TransformerFactory.newInstance().newTransformer()
            transformer.setOutputProperty(OutputKeys.INDENT, "yes")
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
            transformer.transform(xmlInput, xmlOutput)
            return xmlOutput.writer.toString().replaceFirst(">".toRegex(), ">$LINE_SEP")
        } catch (ignored: Exception) {
        }
        return xml
    }

    private fun addLeftBorder(msg: String): String {
        val sb = StringBuilder()
        val lines = msg.split(LINE_SEP).toTypedArray()
        for (line in lines) {
            sb.append(LEFT_BORDER).append(line).append(LINE_SEP)
        }
        return sb.toString()
    }

    private annotation class TYPE

    /** ======================== 使用Log工具 ========================  */
    actual class Builder {
        actual fun enable(): Builder {
            LOG = true
            return this
        }

        actual fun setLogDir(dir: String?): Builder {
            WLog.dir = dir
            return this
        }

        actual fun setLogTag(tag: String?): Builder {
            logTag = tag
            return this
        }

        actual fun enableLogHead(enable: Boolean): Builder {
            logHead = enable
            return this
        }

        actual fun enableLogBorder(enable: Boolean): Builder {
            logBorder = enable
            return this
        }

        actual fun setLogFilter(@TYPE level: Int): Builder {
            logFilter = level
            return this
        }

        actual fun setHeadSep(head_sep: String): Builder {
            WLog.head_sep = head_sep
            return this
        }
    }
}