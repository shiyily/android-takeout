package com.zc.takeout.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context) {
        super(context, "takeout.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE dish  (\n" +
                "  _id bigint PRIMARY KEY,\n" +
                "  name varchar(64),\n" +
                "  price decimal(10, 2),\n" +
                "  image varchar(200),\n" +
                "  category varchar(64),\n" +
                "  description varchar(400)\n" +
                ")";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE custom  (\n" +
                "  account varchar(64),\n" +
                "  tel varchar(11),\n" +
                "  password varchar(32)\n" +
                ")";
        sqLiteDatabase.execSQL(sql);
        sql = "insert into dish values(?,?,?,?,?,?)";
        sqLiteDatabase.execSQL(sql, new Object[]{1, "辣子鸡", 7800.00, "f966a38e-0780-40be-bb52-5699d13cb3d9.jpg", "湘菜", "来自鲜嫩美味的小鸡，值得一尝"});
        sqLiteDatabase.execSQL(sql, new Object[]{2, "毛氏红烧肉", 6800.00, "0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg", "湘菜", "毛氏红烧肉毛氏红烧肉，确定不来一份？"});
        sqLiteDatabase.execSQL(sql, new Object[]{3, "组庵鱼翅", 4800.00, "740c79ce-af29-41b8-b78d-5f49c96e38c4.jpg", "湘菜", "组庵鱼翅，看图足以表明好吃程度"});
        sqLiteDatabase.execSQL(sql, new Object[]{4, "霸王别姬", 12800.00, "057dd338-e487-4bbc-a74c-0384c44a9ca3.jpg", "湘菜", "还有什么比霸王别姬更美味的呢？"});
        sqLiteDatabase.execSQL(sql, new Object[]{5, "全家福", 11800.00, "a53a4e6a-3b83-4044-87f9-9d49b30a8fdc.jpg", "湘菜", "别光吃肉啦，来份全家福吧，让你长寿又美味"});
        sqLiteDatabase.execSQL(sql, new Object[]{6, "邵阳猪血丸子", 13800.00, "2a50628e-7758-4c51-9fbb-d37c61cdacad.jpg", "湘菜", "看，美味不？来嘛来嘛，这才是最爱吖"});
        sqLiteDatabase.execSQL(sql, new Object[]{7, "口味蛇", 16800.00, "0f4bd884-dc9c-4cf9-b59e-7d5958fec3dd.jpg", "湘菜", "爬行界的扛把子，东兴-口味蛇，让你欲罢不能"});
        sqLiteDatabase.execSQL(sql, new Object[]{8, "辣子鸡丁", 8800.00, "ef2b73f2-75d1-4d3a-beea-22da0e1421bd.jpg", "川菜", "辣子鸡丁，辣子鸡丁，永远的魂"});
        sqLiteDatabase.execSQL(sql, new Object[]{9, "麻辣兔头", 19800.00, "2a2e9d66-b41d-4645-87bd-95f2cfeed218.jpg", "川菜", "麻辣兔头的详细制作，麻辣鲜香，色泽红润，回味悠长"});
        sqLiteDatabase.execSQL(sql, new Object[]{10, "蒜泥白肉", 9800.00, "d2f61d70-ac85-4529-9b74-6d9a2255c6d7.jpg", "川菜", "多么的有食欲啊"});
        sqLiteDatabase.execSQL(sql, new Object[]{11, "鱼香肉丝", 3800.00, "8dcfda14-5712-4d28-82f7-ae905b3c2308.jpg", "川菜", "鱼香肉丝简直就是我们童年回忆的一道经典菜，上学的时候点个鱼香肉丝盖饭坐在宿舍床上看着肥皂剧，绝了！现在完美复刻一下上学的时候感觉"});
        sqLiteDatabase.execSQL(sql, new Object[]{12, "麻辣水煮鱼", 14800.00, "1fdbfbf3-1d86-4b29-a3fc-46345852f2f8.jpg", "川菜", "鱼片是买的切好的鱼片，放几个虾，增加味道"});
        sqLiteDatabase.execSQL(sql, new Object[]{13, "鱼香炒鸡蛋", 2000.00, "0f252364-a561-4e8d-8065-9a6797a6b1d3.jpg", "川菜", "鱼香菜也是川味的特色。里面没有鱼却鱼香味"});
        sqLiteDatabase.execSQL(sql, new Object[]{14, "脆皮烧鹅", 12800.00, "e476f679-5c15-436b-87fa-8c4e9644bf33.jpeg", "粤菜", "“广东烤鸭美而香，却胜烧鹅说古冈（今新会），燕瘦环肥各佳妙，君休偏重便宜坊”，可见烧鹅与烧鸭在粤菜之中已早负盛名。作为广州最普遍和最受欢迎的烧烤肉食，以它的“色泽金红，皮脆肉嫩，味香可口”的特色，在省城各大街小巷的烧卤店随处可见。"});
        sqLiteDatabase.execSQL(sql, new Object[]{15, "白切鸡", 6600.00, "9ec6fc2d-50d2-422e-b954-de87dcd04198.jpeg", "粤菜", "白切鸡是一道色香味俱全的特色传统名肴，又叫白斩鸡，是粤菜系鸡肴中的一种，始于清代的民间。白切鸡通常选用细骨农家鸡与沙姜、蒜茸等食材，慢火煮浸白切鸡皮爽肉滑，清淡鲜美。著名的泮溪酒家白切鸡，曾获商业部优质产品金鼎奖。湛江白切鸡更是驰名粤港澳。粤菜厨坛中，鸡的菜式有200余款之多，而最为人常食不厌的正是白切鸡，深受食家青睐。"});
        sqLiteDatabase.execSQL(sql, new Object[]{16, "烤乳猪", 38800.00, "2e96a7e3-affb-438e-b7c3-e1430df425c9.jpeg", "粤菜", "广式烧乳猪主料是小乳猪，辅料是蒜，调料是五香粉、芝麻酱、八角粉等，本菜品主要通过将食材放入炭火中烧烤而成。烤乳猪是广州最著名的特色菜，并且是“满汉全席”中的主打菜肴之一。烤乳猪也是许多年来广东人祭祖的祭品之一，是家家都少不了的应节之物，用乳猪祭完先人后，亲戚们再聚餐食用。"});
        sqLiteDatabase.execSQL(sql, new Object[]{17, "脆皮乳鸽", 10800.00, "3fabb83a-1c09-4fd9-892b-4ef7457daafa.jpeg", "粤菜", "“脆皮乳鸽”是广东菜中的一道传统名菜，属于粤菜系，具有皮脆肉嫩、色泽红亮、鲜香味美的特点，常吃可使身体强健，清肺顺气。随着菜品制作工艺的不断发展，逐渐形成了熟炸法、生炸法和烤制法三种制作方法。无论那种制作方法，都是在鸽子经过一系列的加工，挂脆皮水后再加工而成，正宗的“脆皮乳鸽皮脆肉嫩、色泽红亮、鲜香味美、香气馥郁。这三种方法的制作过程都不算复杂，但想达到理想的效果并不容易。"});
        sqLiteDatabase.execSQL(sql, new Object[]{18, "清蒸河鲜海鲜", 38800.00, "1405081e-f545-42e1-86a2-f7559ae2e276.jpeg", "粤菜", "新鲜的海鲜，清蒸是最好的处理方式。鲜，体会为什么叫海鲜。清蒸是广州最经典的烹饪手法，过去岭南地区由于峻山大岭阻隔，交通不便，经济发展起步慢，自家打的鱼放在锅里煮了就吃，没有太多的讲究，但却发现这清淡的煮法能使鱼的鲜甜跃然舌尖。"});
        sqLiteDatabase.execSQL(sql, new Object[]{19, "老火靓汤", 49800.00, "583df4b7-a159-4cfc-9543-4f666120b25f.jpeg", "粤菜", "老火靓汤又称广府汤，是广府人传承数千年的食补养生秘方，慢火煲煮的中华老火靓汤，火候足，时间长，既取药补之效，又取入口之甘甜。 广府老火汤种类繁多，可以用各种汤料和烹调方法，烹制出各种不同口味、不同功效的汤来。"});
        sqLiteDatabase.execSQL(sql, new Object[]{20, "上汤焗龙虾", 108800.00, "5b8d2da3-3744-4bb3-acdc-329056b8259d.jpeg", "粤菜", "上汤焗龙虾是一道色香味俱全的传统名菜，属于粤菜系。此菜以龙虾为主料，配以高汤制成的一道海鲜美食。本品肉质洁白细嫩，味道鲜美，蛋白质含量高，脂肪含量低，营养丰富。是色香味俱全的传统名菜。"});
        sqLiteDatabase.execSQL(sql, new Object[]{21, "宫保鸡丁", 88800.00, "df23b1de-3f8f-4bb2-9780-373254edb954.jpg", "川菜", ""});
        sqLiteDatabase.execSQL(sql, new Object[]{22, "北冰洋", 500.00, "c99e0aab-3cb7-4eaa-80fd-f47d4ffea694.png", "饮品", ""});
        sqLiteDatabase.execSQL(sql, new Object[]{23, "王老吉", 500.00, "00874a5e-0df2-446b-8f69-a30eb7d88ee8.png", "饮品", ""});
        sqLiteDatabase.execSQL(sql, new Object[]{24, "米饭", 200.00, "ee04a05a-1230-46b6-8ad5-1a95b140fff3.png", "主食", ""});
        sql = "insert into custom values(?,?,?)";
        sqLiteDatabase.execSQL(sql, new Object[]{"jack", "13388283389", "123"});
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
