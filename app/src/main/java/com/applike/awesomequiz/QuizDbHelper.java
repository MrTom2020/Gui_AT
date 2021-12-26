package com.applike.awesomequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.applike.awesomequiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AwesomeQuiz.db";
    public static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + " ( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " + ")";

        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionTable.COLUMN_DIFFICULTY + " TEXT," +
                QuestionTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" + ")";

        db.execSQL(SQL_CREATE_CATEGORY_TABLE);
        db.execSQL(SQL_CREATE_QUESTION_TABLE);
        fillCategoriesTable();
        fillQuestionTable2();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Test_01");
        insertCategory(c1);

        Category c2 = new Category("HTML");
        insertCategory(c2);

    }

    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionTable() {
//        // HTML
//        Question q1 = new Question("Which of the following is used to create Web Pages ?",
//                "C++", "Java", "HTML", 3, Question.DIFFICULTY_EASY , Category.HTML);
//        insertQuestion(q1);
//
//        Question q2 = new Question("HTML stands for ","Hyper Text Makeup Language",
//                "Hyper Tech Markup Language", "none of the above", 1, Question.DIFFICULTY_EASY , Category.HTML);
//        insertQuestion(q2);
//
//        Question q3 = new Question("PC running with Windows 3.x will have _____ extension for HTML page.",".html",
//                "hml", "htm", 1, Question.DIFFICULTY_MEDIUM , Category.HTML);
//        insertQuestion(q3);
//
//        Question q4 = new Question("HTML was firstly proposed in year","1990",
//                "1995", "1197", 2, Question.DIFFICULTY_MEDIUM , Category.HTML);
//        insertQuestion(q4);
//
//        Question q5 = new Question("HTML tags are surrounded by _____ brackets.","Squart",
//                "Angle", "Round", 1, Question.DIFFICULTY_HARD , Category.HTML);
//        insertQuestion(q5);
//
//        Question q6 = new Question("HTML program can be read and rendered by","Compiler",
//                "Browser", "Server", 2, Question.DIFFICULTY_HARD , Category.HTML);
//        insertQuestion(q6);
//
//        // JAVA
//        Question q10 = new Question("Which of these keywords is used to define packages in Java ?",
//                "Package", "package", "pkg", 1, Question.DIFFICULTY_EASY , Category.JAVA);
//        insertQuestion(q10);
//
//        Question q20 = new Question("Which of the following is a keyword in Java ?","this",
//                "that", "it", 1, Question.DIFFICULTY_EASY , Category.JAVA);
//        insertQuestion(q20);
//
//        Question q30 = new Question(" Which of the following is a valid declaration of an object of class Box ?","obj = new Box();",
//                "Box obj = new Box;", "Box obj = new Box();", 3, Question.DIFFICULTY_MEDIUM , Category.JAVA);
//        insertQuestion(q30);
//
//        Question q40 = new Question("Which of these operators is used to allocate memory for an object ?","give",
//                "new", "alloc", 2, Question.DIFFICULTY_MEDIUM , Category.JAVA);
//        insertQuestion(q40);
//
//        Question q50 = new Question("What will be the return type of a method that not returns any value ?","double",
//                "void", "int", 2, Question.DIFFICULTY_HARD , Category.JAVA);
//        insertQuestion(q50);
//
//        Question q60 = new Question("Which of the modifier can not be used for constructors ?","static",
//                "private", "public", 3, Question.DIFFICULTY_HARD , Category.JAVA);
//        insertQuestion(q60);

    }
    private void fillQuestionTable2() {
        // HTML
        Question q1 = new Question("Phương án nào sau đây là thông tin ?",
                "Các con số thu thập được qua cuộc điều tra dân số",
                "Kiến thức về phân bố dân cư.",
                "Phiếu điều tra dân số.",
                "Tệp lưu trữ tài liệu về điều tra dân số.",2, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q1);

        Question q2 = new Question(
                "Phát biểu nào sau đây là đúng ? ",
                "Dữ liệu chỉ có thể hiểu bởi những người có trình độ cao ",
                "Dữ liệu là những giá trị số do con người nghĩ ra",
                "Dữ liệu được thể hiện dưới dạng con số, văn bản, hình ảnh, âm thanh",
                "Dữ liệu chỉ có ở trong máy tính",3, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q2);

        Question q3 = new Question("Phát biểu nào sau đây là đúng ?",
                "Thông tin là kết quả của việc xử lý dữ liệu để nó trở nên có ý nghĩa.",
                "Mọi thông tin muốn có được, con người sẽ phải tốn rất nhiều tiền.",
                "Không có sự phân biệt giữa thông tin và dữ liệu.",
                "Không có sự phân biệt giữa thông tin và dữ liệu."
                ,1, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q3);

        Question q4 = new Question("Xem bản tin dự báo thời tiết, bạn Khoa kết luận: “Hôm nay, trời có mưa”. Phát biểu nào sau đây là đúng ?",
                "Không có sự phân biệt giữa thông tin và dữ liệu.",
                "Không có sự phân biệt giữa thông tin và dữ liệu.",
                "Không có sự phân biệt giữa thông tin và dữ liệu.",
                "Bản tin dự báo thời tiết và kết luận của Khoa đều là dữ liệu.",
                1, Question.DIFFICULTY_MEDIUM , Category.HTML);
        insertQuestion(q4);

        Question q5 = new Question("Công cụ nào sau đây không phải vật mang tin ?",
                "Giấy.",
                "Cuộn phim.",
                "Thẻ nhớ.",
                "Xô, chậu.",4, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q5);

        Question q6 = new Question("Phát biểu nào sau đây đúng về lợi ích của thông tin ?",
                "Có độ tin cậy cao, đem lại hiểu biết cho con người.",
                "Đem lại hiểu biết cho con người, không phụ thuộc vào dữ liệu.",
                "Đem lại hiểu biết cho con người, không phụ thuộc vào dữ liệu.",
                "Đem lại hiểu biết cho con người, không phụ thuộc vào dữ liệu.",4, Question.DIFFICULTY_EASY, Category.HTML);
        insertQuestion(q6);

        // JAVA
        Question q7 = new Question("Quá trình xử lý thông tin bao gồm mấy hoạt động ?",
                "2"
                , "3"
                , "4",
                "5", 3, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q7);

        Question q8 = new Question("Thuyết trình chủ đề tình bạn trước lớp được xếp vào hoạt động nào trong quá trình xử lý thông tin ?",
                "Thu nhận.",
                "Lưu trữ.",
                "Xử lý.",
                "Truyền.", 4 ,Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q8);

        Question q9 = new Question("Lắng nghe giáo viên giảng bài được xếp vào hoạt động nào trong quá trình xử lý thông tin ?",
                "Thu nhận.",
                "Lưu trữ. "
                , "Xử lý."
                ,"Truyền.", 1, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q9);

        Question q10 = new Question("Hãy cho biết thông tin nào con người có thể thu nhận còn máy tính thì  không ?",
                "Hình ảnh.",
                "Văn bản, con số.",
                "Cảm giác, mùi vị.",
                "Âm thanh.",
                3, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q10);

        Question q11 = new Question("Các thao tác lập luận, giải thích, phân tích, phán đoán, tưởng tượng, ... của con người được xếp vào hoạt động nào trong quá trình xử lý thông tin ?",
                 "Thu nhận.",
                 "Lưu trữ.",
                 "Xử lý.",
                 "Truyền."
                , 3, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q11);

        Question q12 = new Question("Bộ phận nào của máy tính thực hiện hoạt động lưu trữ thông tin ?",
                "Bộ nhớ.",
                 "Bộ xử lý CPU.",
                "Thiết bị ra.",
                "Thiết bị ra."

                ,2, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q12);

        Question q13 = new Question("Bàn phím, chuột, máy quét, webcam là những ví dụ về loại thiết bị nào của máy tính ?",
                 "Thiết bị vào.",
                "Bộ nhớ.",
                 "Bộ xử lý CPU.",
                "Thiết bị ra."
                , 1, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q13);

        Question q14 = new Question("Trong các thiết bị sau, đâu không phải là thiết bị ra ?",
                 "Máy in.",
                "Loa.",
                "Màn hình.",
                 "Micro."
                , 4, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q14);

        Question q15 = new Question("Đặc điểm nào sau đây không thuộc về máy tính ?",
                "Thực hiện nhanh, chính xác.",
                "Suy nghĩ sáng tạo.",
                 "Lưu trữ lớn.",
                 "Hoạt động bền bỉ."
                , 2, Question.DIFFICULTY_MEDIUM , Category.HTML);
        insertQuestion(q15);

        Question q16 = new Question("Dãy bit là gì ?",
                "Là những dãy kí hiệu 0 và 1.",
        "Là âm thanh phát ra từ máy tính.",
        "Là một dãy chỉ gồm dãy số 2.",
        "Biểu diễn số, văn bản, hình ảnh, âm thanh."
                ,1, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q16);

        Question q17 = new Question("Máy tính sử dụng dãy bit để làm gì ?",
                "Biểu diễn các số.",
                "Biểu diễn văn bản.",
                 "Biểu diễn âm thanh, hình ảnh.",
                "Biểu diễn các số, văn bản, hình ảnh và âm thanh."
                ,4, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q17);

        Question q18 = new Question("Dữ liệu được máy tính lưu trữ dưới dạng:",
                 "Thông tin.",
                "Số thập phân.",
                 "Dãy bit.",
                 "Các kí tự."
                ,3, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q18);

        Question q19 = new Question("Vì sao dữ liệu trong máy tính được mã hóa thành dãy bit ?",
                 "Dãy bit đáng tin cậy hơn.",
                 "Máy tính chỉ làm việc được với hai kí tự 0 và 1.",
         "Dãy bit chiếm ít dung lượng nhớ hơn.",
        "Dãy bít được xử lý dễ dàng hơn."
,2, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q19);

        Question q20 = new Question("Số 15 được mã hóa thành dãy số nào sau đây ?",
                "1001.",
        "0101.",
         "1011.",
        "1111."
                ,18, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q20);

        Question q21 = new Question("Trong các đơn vị sau, đơn vị đo dung lượng thông tin lớn nhất là gì ?",
                 "Terabyte.",
                         "Megabyte.",
                        "Kilobyte.",
                         "Gigabyte.",

                4, Question.DIFFICULTY_MEDIUM , Category.HTML);
        insertQuestion(q21);

        Question q22 = new Question("Bao nhiêu byte tạo thành 1 “kilobyte” ?",
                 "8.",
        "10.",
        "32.",
        "1024.",

        4, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q22);

        Question q23 = new Question("Một terabyte xắp xỉ bằng ?","Một triệu byte.",
                "Một tỷ byte.",
                "Một nghìn tỷ byte.",
                "Một nghìn byte.",

        3, Question.DIFFICULTY_EASY, Category.HTML);
        insertQuestion(q23);

        Question q24 = new Question("Khả năng lưu trữ của một thiết bị nhớ được gọi là gì ?",
                 "Dung lượng nhớ.",
                "Khối lượng nhớ.",
                 "Thể tích nhớ.",
                "Năng lượng nhớ.",

                1, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q24);

        Question q25 = new Question("Một thẻ nhớ có dung lượng 2GB có thể chứa được bao nhiêu bức ảnh, biết mỗi bức ảnh có dung lượng 6MB ?",
                 "342.",
        "341.",
        "600",
        "2048."
                ,2, Question.DIFFICULTY_HARD , Category.HTML);
        insertQuestion(q25);

        Question q26 = new Question("Một mạng máy tính bao gồm ?",
                "Tối thiểu 10 máy tính được liên kết với nhau.",
                 "Một số máy tính bàn.",
                "Hai hoặc nhiều máy tính được kết nối với nhau.",
                "Tất cả các máy tính trong một phòng hoặc trong một tòa nhà."
                ,24, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q26);

        Question q27 = new Question("Trong các phát biểu sau, phát biểu nào sai ?",
                "Tối thiểu 10 máy tính được liên kết với nhau.",
                 "Một số máy tính bàn.",
                "Hai hoặc nhiều máy tính được kết nối với nhau.",
                 "Tất cả các máy tính trong một phòng hoặc trong một tòa nhà."
                ,3, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q27);

        Question q28 = new Question("Trong các lợi ích sau, lợi ích nào không phải của việc sử dụng máy tính ?",
                 "Trong một mạng máy tính, các tài nguyên như máy in có thể được chia sẻ.",
                 "Virus có thể lây sang các máy tính khác trong mạng máy tính.",
                "Người sử dụng có thể giao tiếp với nhau trên mạng máy tính.",
                "Người sử dụng không thể chia sẻ dữ liệu trên máy tính của mình cho người khác trong cùng một mạng máy tính."
                ,4, Question.DIFFICULTY_MEDIUM , Category.HTML);
        insertQuestion(q28);

        Question q29 = new Question("Trong các thiết bị sau đây, đâu là thiết bị đầu cuối ?",
                "Bộ định tuyến.",
                 "Bộ chia.",
                 "Phần mềm mạng.",
                "Máy tính bàn."
                ,4, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q29);

        Question q30 = new Question("Có mấy cách kết nối các máy tính trong mạng máy tính ?",
                 "Giảm chi phí khi dùng chung phần cứng.",
                  "Người sử dụng có quyền kiểm soát độc quyền đối với dữ liệu và ứng dụng riêng của họ.",
                 "Giảm chi phí khi dùng chung phần mềm.",
                 "Cho phép chia sẻ dữ liệu, tăng hiệu quả sử dụng."
                ,3, Question.DIFFICULTY_MEDIUM , Category.HTML);
        insertQuestion(q30);

        Question q31 = new Question("Trong các phát biểu sau, phát biểu nào sai ?",
                "Mạng có dây kết nối các máy tính bằng dây dẫn mạng.",
                "Mạng không dây có thể kết nối ở mọi địa hình.",
                "Mạng không dây không chỉ kết nối các máy tính mà còn cho phép kết nối các thiết bị thông minh khác như: điện thoại di động, tivi, tủ lạnh, ...",
         "Mạng có dây dễ sửa và lắp đặt hơn mạng không dây vì có thể nhìn thấy được dây dẫn."
                ,4, Question.DIFFICULTY_MEDIUM , Category.HTML);
        insertQuestion(q31);

        Question q32 = new Question("Mạng máy tính gồm các thành phần ?",
                "Máy tính và thiết bị kết nối.",
                 "Thiết bị đầu cuối và thiết bị kết nối.",
                "Thiết bị đầu cuối, thiết bị kết nối và phần mềm mạng.",
                "Máy tính và phần mềm mạng.",

                3, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q32);

        Question q33 = new Question("Để kết nối với Internet, máy tính phải được cài đặt và cung cấp dịch vụ bởi:",
                "Người quản trị mạng máy tính.",
                "Người quản trị mạng xã hội.",
                "Nhà cung cấp dịch vụ Internet.",
                 "Một máy tính khác."
                ,3, Question.DIFFICULTY_EASY, Category.HTML);
        insertQuestion(q33);

        Question q34 = new Question("Phát biểu nào sau đây không đúng khi nói về Internet ?",
                 "Một mạng kết nối các hệ thống máy tính và các thiết bị với nhau giúp người sử dụng có thể xem, tìm kiếm, " +
                "chia sẻ và trao đổi thông tin, ...",
         "Một mạng công cộng không thuộc sở hữu hay do bất kì một tổ chức hoặc cá nhân nào điều hành.",
                "Một mạng lưới rộng lớn kết nối hàng triệu máy tính trên khắp thế giới.",
        "Một mạng kết nối các máy tính với nhau được tổ chức và giám sát bởi một cơ quan quản lí."
                ,4, Question.DIFFICULTY_MEDIUM , Category.HTML);
        insertQuestion(q34);

        Question q35 = new Question("Phát biểu nào sau đây không phải đặc điểm của Internet ?",
                 "Phạm vi hoạt động trên toàn cầu.",
                "Có nhiều dịch vụ đa dạng và phong phú.",
                 "Không thuộc sở hữu của ai.",
                "Thông tin chính xác tuyệt đối."
                ,4, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q35);

        Question q36 = new Question("Phát biểu nào sau đây không phải lợi ích của việc sử dụng Internet ?",
                 "Giúp tiết kiệm thời gian và cung cấp nhiều tư liệu làm bài tập dự án.",
                 "Giúp nâng cao kiến thức bằng cách tham gia các khóa học trực tuyến.",
                "Giúp giải trí bằng cách xem mạng xã hội và chơi điện tử suốt cả ngày.",
                "Giúp mở rộng giao lưu kết bạn với các bạn ở nước ngoài."
                ,3, Question.DIFFICULTY_MEDIUM , Category.HTML);
        insertQuestion(q36);

        Question q37 = new Question("Internet cung cấp bao nhiêu dịch vụ cho người sử dụng ?",
                "1",
        "5",
        "100",
        "Rất nhiều"
,4, Question.DIFFICULTY_EASY , Category.HTML);
        insertQuestion(q37);
    }
    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(cursor.getColumnIndex(CategoriesTable._ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestion() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String selection = QuestionTable.COLUMN_CATEGORY_ID + "= ? " +
                " AND " + QuestionTable.COLUMN_DIFFICULTY + " = ?";

        String[] selectionArgs = new String[] {String.valueOf(categoryID), difficulty};

        Cursor cursor = db.query(QuestionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,null,null);

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }
}