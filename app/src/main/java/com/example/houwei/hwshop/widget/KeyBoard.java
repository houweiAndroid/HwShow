package com.example.houwei.hwshop.widget;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.houwei.hwshop.R;

import java.util.ArrayList;
import java.util.List;

public class KeyBoard {

    private Activity activity;
    private Handler zhandler;

    public KeyBoard(Activity activity, Handler zhandler) {
        this.activity = activity;
        this.zhandler = zhandler;

        // line = activity.findViewById(R.id.line);
    }

    // 模拟键盘按键
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button night;
    private Button zero;
    private LinearLayout other;
    private LinearLayout shanchu;
    private Button allClean;
    private Button shouqian;
    private Button dot;
    private TextView addTxtView;
    private Typeface typeface;
    private MoneyTextView keyboardEdit;
    private TextView amountNumView;
    private KeyOtherListenr listener;
    private int curlimit = 15;// 默认输入框长度不能超过15
    private boolean ifmoney = true;// 默认输入为金额
    private List<String> numList;
    private String currInputNum;
    private int currMode;
    private boolean isPlus;
    private double amount;

    private View line;
    private int moveHeight;
    private boolean lineIsOpen;

    public void setKeyBoardEdit(MoneyTextView ed, TextView tv, int limit,
                                boolean usepoint) {
        if (null != ed) {
            keyboardEdit = ed;
            amountNumView = tv;
            curlimit = limit;
            ifmoney = usepoint;
        } else {
            keyboardEdit = null;
        }

        numList = new ArrayList<String>();
        currInputNum = "";
        keyboardEdit.setText("0");
        moveHeight = tv.getMeasuredHeight();
        amountNumView.setVisibility(View.GONE);
        lineIsOpen = true;

    }

    // public OtherCashModel getOtherCashModel() {
    // OtherCashModel model = null;
    // try {
    // if (null != keyboardEdit) {
    // model = (OtherCashModel) keyboardEdit.getTag();
    // }
    //
    // } catch (Exception e) {
    //
    // }
    // return model;
    // }

    // 如为金额，金额整数不能超过7为，小数点不能超过2位，按银联规则输入
    private static final int MLength = 7;
    private static final int MLength1 = 2;
    private static final int MAX_LENGTH = 9;

    // 虚拟按键金额输入框增加输入字符
    // public void addKeyBoard(String ad) {
    // if (null != keyboardEdit) {
    // // String k = keyboardEdit.getText().toString();
    // String k = currInputNum;
    // if (null == k) {
    // k = "";
    // }
    // if (null != k) {
    // k = k.replace(",", "");
    //
    // if (k.length() >= (curlimit)) {
    // Toast.makeText(
    // activity,
    // String.format(activity.getString(R.string.textout),
    // curlimit), 3000).show();
    // } else {
    // if (ifmoney) {
    // if (ad.equals("0") || ad.equals("00")) {
    // if (k.length() > 0) {
    // try {
    // double y = Double.parseDouble(k);
    // if (y != 0) {
    // if (k.indexOf("0.00") == 0) {
    // k = k.replace("0.00", "");
    // } else {
    // if (k.indexOf("0.0") == 0) {
    // k = k.replace("0.0", "");
    // } else {
    // if (k.indexOf("0.") == 0) {
    // k = k.replace("0.", "");
    // } else {
    // if (k.indexOf(".") >= 0) {
    // k = k.replace(".", "");
    // }
    // }
    // }
    // }
    //
    // if ((ad.equals("0") && k.length() < (MLength + MLength1))
    // || (ad.equals("00") && k
    // .length() < (MLength
    // + MLength1 - 1))) {
    // k = k + ad;
    // if (k.length() == 1) {
    // k = "0.0" + k;
    // } else if (k.length() == 2) {
    // k = "0." + k;
    // } else if (k.length() > 2) {
    // k = (k.substring(0,
    // k.length() - 2))
    // + "."
    // + k.substring(
    // k.length() - 2,
    // k.length());
    // }
    //
    //
    //
    // currInputNum=k;
    // keyboardEdit.setText(k);
    // }
    //
    // }
    // } catch (Exception e) {
    //
    // }
    // } else {
    // if (k.length() == 0) {
    // k = "0.00";
    // keyboardEdit.setText(k);
    // }
    // }
    //
    // } else if (ad.equals(".")) {
    // if (k.length() == 0) {
    // k = "0.00";
    // keyboardEdit.setText(k);
    // }
    // } else {
    // if (k.indexOf("0.00") == 0) {
    // k = k.replace("0.00", "");
    // } else {
    // if (k.indexOf("0.0") == 0) {
    // k = k.replace("0.0", "");
    // } else {
    // if (k.indexOf("0.") == 0) {
    // k = k.replace("0.", "");
    // } else {
    // if (k.indexOf(".") >= 0) {
    // k = k.replace(".", "");
    // }
    // }
    // }
    // }
    // if (k.length() < (MLength + MLength1)) {
    // k = k + ad;
    // if (k.length() == 1) {
    // k = "0.0" + k;
    // } else if (k.length() == 2) {
    // k = "0." + k;
    // } else if (k.length() > 2) {
    // k = (k.substring(0, k.length() - 2))
    // + "."
    // + k.substring(k.length() - 2,
    // k.length());
    // }
    //
    // keyboardEdit.setText(k);
    // }
    //
    // }
    //
    // } else {
    // int index = keyboardEdit.getSelectionStart();
    // Editable editable = keyboardEdit.getText();
    // editable.insert(index, ad);
    // }
    //
    // }
    //
    // } else {
    //
    // keyboardEdit.setText(ad);
    // keyboardEdit.setSelection(keyboardEdit.getText().toString()
    // .length());
    //
    // }
    // try{
    // OtherCashModel model = (OtherCashModel)keyboardEdit.getTag();
    // if(null!=model)
    // {
    // model.setEditstr(keyboardEdit.getText().toString());
    // }
    // }catch(Exception e){
    //
    // }
    //
    // }
    // }

    public void addKeyBoard(String ad) {
        if (null != keyboardEdit) {
            // String k = keyboardEdit.getText().toString();
            String k = currInputNum;
            if (null == k) {
                k = "";
            }
            if (null != k) {
                k = k.replace(",", "");

                if (k.length() >= (curlimit)) {
                    Toast.makeText(
                            activity,
                            String.format(activity.getString(R.string.textout),
                                    curlimit), 3000).show();
                } else {
                    if (ifmoney) {
                        if (ad.equals("0") || ad.equals("00")) {
                            if (k.length() > 0) {
                                try {
                                    double y = Double.parseDouble(k);
                                    if (y != 0) {
                                        if (k.indexOf("0.00") == 0) {
                                            k = k.replace("0.00", "");
                                        } else {
                                            if (k.indexOf("0.0") == 0) {
                                                k = k.replace("0.0", "");
                                            } else {
                                                if (k.indexOf("0.") == 0) {
                                                    k = k.replace("0.", "");
                                                } else {
                                                    if (k.indexOf(".") >= 0) {
                                                        k = k.replace(".", "");
                                                    }
                                                }
                                            }
                                        }

                                        if ((ad.equals("0") && k.length() < (MLength + MLength1))
                                                || (ad.equals("00") && k
                                                .length() < (MLength
                                                + MLength1 - 1))) {
                                            k = k + ad;
                                            if (k.length() == 1) {
                                                k = "0.0" + k;
                                            } else if (k.length() == 2) {
                                                k = "0." + k;
                                            } else if (k.length() > 2) {
                                                k = (k.substring(0,
                                                        k.length() - 2))
                                                        + "."
                                                        + k.substring(
                                                        k.length() - 2,
                                                        k.length());
                                            }

                                            currInputNum = k;
                                            keyboardEdit.setText(k);
                                        }

                                    }
                                } catch (Exception e) {

                                }
                            } else {
                                if (k.length() == 0) {
                                    k = "0.00";
                                    keyboardEdit.setText(k);
                                }
                            }

                        } else if (ad.equals(".")) {
                            if (k.length() == 0) {
                                k = "0.00";
                                keyboardEdit.setText(k);
                            }
                        } else {
                            if (k.indexOf("0.00") == 0) {
                                k = k.replace("0.00", "");
                            } else {
                                if (k.indexOf("0.0") == 0) {
                                    k = k.replace("0.0", "");
                                } else {
                                    if (k.indexOf("0.") == 0) {
                                        k = k.replace("0.", "");
                                    } else {
                                        if (k.indexOf(".") >= 0) {
                                            k = k.replace(".", "");
                                        }
                                    }
                                }
                            }
                            if (k.length() < (MLength + MLength1)) {
                                k = k + ad;
                                if (k.length() == 1) {
                                    k = "0.0" + k;
                                } else if (k.length() == 2) {
                                    k = "0." + k;
                                } else if (k.length() > 2) {
                                    k = (k.substring(0, k.length() - 2))
                                            + "."
                                            + k.substring(k.length() - 2,
                                            k.length());
                                }

                                keyboardEdit.setText(k);
                            }

                        }

                    } else {

                        currInputNum = k + ad;

                        keyboardEdit.setText(currInputNum);

                        // int index = keyboardEdit.getSelectionStart();
                        // Editable editable = keyboardEdit.getText();
                        // editable.insert(index, ad);
                    }

                }

            } else {

                keyboardEdit.setText(ad);
                // keyboardEdit.setSelection(keyboardEdit.getText().toString()
                // .length());

            }
            try {
                // OtherCashModel model = (OtherCashModel)
                // keyboardEdit.getTag();
                // if (null != model) {
                // model.setEditstr(keyboardEdit.getText().toString());
                // }
            } catch (Exception e) {

            }

        }
    }

    // 输入普通数字，非金额输入框增加输入字符
    public void addKeyBoardFirst(String ad) {

        if (isPlus) {
            return;
        }

        if (null != keyboardEdit) {
            // String k = keyboardEdit.getText().toString();
            String k = currInputNum;

            if (null != k) {
                if ((k.length() + ad.length()) >= curlimit) {
                    Toast.makeText(
                            activity,
                            String.format(activity.getString(R.string.textout),
                                    curlimit), 3000).show();
                } else {
                    if (ifmoney) {
                        boolean ifadd = false;
                        int point = k.indexOf(".");

                        if (point >= 0) {
                            int point1 = k.length() - (point + 1);
                            if (point <= MLength && point1 < MLength1) {
                                ifadd = true;
                            }
                        } else {
                            if (k.length() < MLength) {
                                ifadd = true;
                            }
                        }
                        if (ifadd) {
                            if (!k.equals("0") && !k.equals("00")) {

                            } else {
                                keyboardEdit.setText("");
                            }
                            int index = keyboardEdit.getSelectionStart();
                            // Editable editable = keyboardEdit.getText();
                            // editable.insert(index, ad);
                        }
                    } else {
                        // int index = keyboardEdit.getSelectionStart();
                        // Editable editable = keyboardEdit.getText();
                        // editable.insert(index, ad);
                        if (canAdd(k, ad)) {

                            if (k.length() == 0 && ad.equals(".")) {

                                currInputNum = "0" + ad;
                            } else {
                                currInputNum = k + ad;
                            }

                            if (k.equals("0") && !ad.equals(".")) {
                                currInputNum = ad;
                            }

                            if (numList.size() > 0) {
                                StringBuffer buffer = new StringBuffer("");
                                for (String str : numList) {
                                    buffer.append(str + "+");
                                }

                                keyboardEdit.setText(buffer.toString()
                                        + currInputNum);

                            } else {
                                keyboardEdit.setText(currInputNum);
                            }
                        }
                    }

                }

            } else {
                // if(!ad.equals("0")&&!ad.equals("00"))
                {
                    keyboardEdit.setText(ad);
                    // keyboardEdit.setSelection(keyboardEdit.getText().toString()
                    // .length());
                }

            }

            try {
                // OtherCashModel model = (OtherCashModel)
                // keyboardEdit.getTag();
                // if (null != model) {
                // model.setEditstr(keyboardEdit.getText().toString());
                // }
            } catch (Exception e) {

            }
        }
    }

    private boolean canAdd(String num, String ad) {
        boolean res = true;
        // if (num.length() == 0 && ad.equals("0")) {
        // return false;
        // }
        if (num.equals("0") && ad.equals("0")) {
            return false;
        }

        if (num.replace(".", "").length() >= MAX_LENGTH) {
            return false;
        }

        // 不允许2个点
        if (ad.equals(".")) {
            if (num.indexOf(".") != -1) {
                return false;
            }
        } else {
            if (num.indexOf(".") != -1) {
                String l = num.substring(num.indexOf(".") + 1, num.length());
                if (l.length() >= 2) {
                    return false;
                }
            }
        }

        return res;
    }

    // 删除edittext上的一个字符
    public void deleteKeyBoard() {
        if (null != keyboardEdit) {
            // String k = keyboardEdit.getText().toString();
            String k = currInputNum;
            if (null != k) {
                if (k.length() > 0) {
                    if (ifmoney) {
                        // k = k.substring(0, k.length()-1);
                        // keyboardEdit.setText(k);

                        k = k.replace(",", "");
                        if (k.indexOf("0.00") == 0) {
                            k = k.replace("0.00", "");
                        } else {
                            if (k.indexOf("0.0") == 0) {
                                k = k.replace("0.0", "");
                            } else {
                                if (k.indexOf("0.") == 0) {
                                    k = k.replace("0.", "");
                                } else {
                                    if (k.indexOf(".") >= 0) {
                                        k = k.replace(".", "");
                                    }
                                }
                            }
                        }
                        if (k.length() > 0) {
                            k = k.substring(0, k.length() - 1);
                        }

                        if (k.length() == 0) {
                            k = "0.00";
                        } else if (k.length() == 1) {
                            k = "0.0" + k;
                        } else if (k.length() == 2) {
                            k = "0." + k;
                        } else if (k.length() > 2) {
                            k = (k.substring(0, k.length() - 2)) + "."
                                    + k.substring(k.length() - 2, k.length());
                        }

                        keyboardEdit.setText(k);

                    } else {
                        // int index = keyboardEdit.getSelectionStart();
                        // if (index > 0) {
                        // Editable editable = keyboardEdit.getText();
                        // editable.delete(index - 1, index);
                        // }
                        if (currInputNum.length() > 0) {
                            currInputNum = currInputNum.substring(0,
                                    currInputNum.length() - 1);

                            if (currInputNum.equals("")) {
                                keyboardEdit.setText("0");
                            } else {
                                keyboardEdit.setText(currInputNum);
                            }
                        }

                    }
                    // keyboardEdit.setText(k.substring(0, k.length()-1));
                    // keyboardEdit.setSelection(keyboardEdit.getText().toString().length());

                }
            }
            try {
                // OtherCashModel model = (OtherCashModel)
                // keyboardEdit.getTag();
                // if (null != model) {
                // model.setEditstr(keyboardEdit.getText().toString());
                // }
            } catch (Exception e) {

            }
        }
    }

    // public void deleteKeyBoard() {
    // if (null != keyboardEdit) {
    // String k = keyboardEdit.getText().toString();
    // if (null != k) {
    // if (k.length() > 0) {
    // if (ifmoney) {
    // // k = k.substring(0, k.length()-1);
    // // keyboardEdit.setText(k);
    //
    // k = k.replace(",", "");
    // if (k.indexOf("0.00") == 0) {
    // k = k.replace("0.00", "");
    // } else {
    // if (k.indexOf("0.0") == 0) {
    // k = k.replace("0.0", "");
    // } else {
    // if (k.indexOf("0.") == 0) {
    // k = k.replace("0.", "");
    // } else {
    // if (k.indexOf(".") >= 0) {
    // k = k.replace(".", "");
    // }
    // }
    // }
    // }
    // if (k.length() > 0) {
    // k = k.substring(0, k.length() - 1);
    // }
    //
    // if (k.length() == 0) {
    // k = "0.00";
    // } else if (k.length() == 1) {
    // k = "0.0" + k;
    // } else if (k.length() == 2) {
    // k = "0." + k;
    // } else if (k.length() > 2) {
    // k = (k.substring(0, k.length() - 2)) + "."
    // + k.substring(k.length() - 2, k.length());
    // }
    //
    // keyboardEdit.setText(k);
    //
    // } else {
    // int index = keyboardEdit.getSelectionStart();
    // if (index > 0) {
    // Editable editable = keyboardEdit.getText();
    // editable.delete(index - 1, index);
    // }
    // }
    // // keyboardEdit.setText(k.substring(0, k.length()-1));
    // // keyboardEdit.setSelection(keyboardEdit.getText().toString().length());
    //
    // }
    // }
    // try {
    // OtherCashModel model = (OtherCashModel) keyboardEdit.getTag();
    // if (null != model) {
    // model.setEditstr(keyboardEdit.getText().toString());
    // }
    // } catch (Exception e) {
    //
    // }
    // }
    // }

    // 初始化模拟键盘
    public void initKeyBoard(View view, View.OnClickListener listener) {
        one = (Button) view.findViewById(R.id.one);
        one.setOnClickListener(listener);
        two = (Button) view.findViewById(R.id.two);
        two.setOnClickListener(listener);
        three = (Button) view.findViewById(R.id.three);
        three.setOnClickListener(listener);
        four = (Button) view.findViewById(R.id.four);
        four.setOnClickListener(listener);
        five = (Button) view.findViewById(R.id.five);
        five.setOnClickListener(listener);
        six = (Button) view.findViewById(R.id.six);
        six.setOnClickListener(listener);
        seven = (Button) view.findViewById(R.id.seven);
        seven.setOnClickListener(listener);
        eight = (Button) view.findViewById(R.id.eight);
        eight.setOnClickListener(listener);
        night = (Button) view.findViewById(R.id.night);
        night.setOnClickListener(listener);
        zero = (Button) view.findViewById(R.id.zero);
        zero.setOnClickListener(listener);
        other = (LinearLayout) view.findViewById(R.id.other);
        other.setOnClickListener(listener);
        shanchu = (LinearLayout) view.findViewById(R.id.shanchu);
        shanchu.setOnClickListener(listener);
        shanchu.setLongClickable(true);

        allClean = (Button) view.findViewById(R.id.allClean);
        shouqian = (Button) view.findViewById(R.id.shouqian);
        dot = (Button) view.findViewById(R.id.dot);
        dot.setOnClickListener(listener);
        allClean.setOnClickListener(listener);
        changeShouqianButton(1);

//		typeface = Typeface.createFromAsset(activity.getAssets(),
//				Constants.NUM_FONT);
        one.setTypeface(typeface);
        two.setTypeface(typeface);
        three.setTypeface(typeface);
        four.setTypeface(typeface);
        five.setTypeface(typeface);
        six.setTypeface(typeface);
        seven.setTypeface(typeface);
        eight.setTypeface(typeface);
        night.setTypeface(typeface);
        zero.setTypeface(typeface);
        allClean.setTypeface(typeface);
        shouqian.setTypeface(typeface);
        dot.setTypeface(typeface);
        if (keyboardEdit != null) {
            keyboardEdit.setTypeface(typeface);
        }
        if (amountNumView != null) {
            amountNumView.setTypeface(typeface);
        }

        addTxtView = (TextView) view.findViewById(R.id.addTextView);
        addTxtView.setTypeface(typeface);

        shanchu.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                if (ifmoney) {

                } else {
                    if (null != cancleThread) {
                        setTimeStop();
                    }
                    setTimeStart();
                }

                return true;
            }
        });
        shanchu.setOnTouchListener(new OnTouchListener() {

            // @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:

                        break;

                    case MotionEvent.ACTION_DOWN:

                        break;

                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        setTimeStop();
                        break;
                }
                return shanchu.onTouchEvent(event);
            }
        });

        // if(AppData.isTablet){
//		ImageView imageView = (ImageView) shanchu.getChildAt(0);
//		final int imgSize = (int) (SCREEN_WIDTH * 0.086f);
//		imageView.getLayoutParams().width = imgSize;
//		imageView.getLayoutParams().height = imgSize;
        // }
    }

    public void setKeyListener(KeyOtherListenr listener) {
        this.listener = listener;
    }

    // 开始模拟键盘长按响应
    public void setTimeStart() {
        cancleThread = new TimeThread();
        cancleThread.start();
    }

    // 停止模拟键盘长按响应
    public void setTimeStop() {
        try {
            if (null != cancleThread && !cancleThread.isInterrupted()) {
                cancleThread.ifstop = false;
                cancleThread.interrupt();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public TimeThread cancleThread;
    public static final int Cancle = 1001;

    public class TimeThread extends Thread {
        public boolean ifstop = true;

        public void run() {
            do {
                try {
                    Thread.sleep(100);
                    zhandler.sendMessage(zhandler.obtainMessage(Cancle));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (ifstop && null != keyboardEdit
                    && keyboardEdit.getSelectionStart() > 0);

        }

    }

    // 点击模拟键盘响应函数
    public void clickKeyBoard(View arg0) {
        other.setSelected(false);
        switch (arg0.getId()) {
            case R.id.one: {
                if (ifmoney) {
                    addKeyBoard("1");
                } else {
                    addKeyBoardFirst("1");
                }
                break;
            }
            case R.id.two: {
                if (ifmoney) {
                    addKeyBoard("2");
                } else {
                    addKeyBoardFirst("2");
                }
                break;
            }
            case R.id.three: {
                if (ifmoney) {
                    addKeyBoard("3");
                } else {
                    addKeyBoardFirst("3");
                }
                break;
            }
            case R.id.four: {
                if (ifmoney) {
                    addKeyBoard("4");
                } else {
                    addKeyBoardFirst("4");
                }
                break;
            }
            case R.id.five: {
                if (ifmoney) {
                    addKeyBoard("5");
                } else {
                    addKeyBoardFirst("5");
                }
                break;
            }
            case R.id.six: {
                if (ifmoney) {
                    addKeyBoard("6");
                } else {
                    addKeyBoardFirst("6");
                }
                break;
            }
            case R.id.seven: {
                if (ifmoney) {
                    addKeyBoard("7");
                } else {
                    addKeyBoardFirst("7");
                }
                break;
            }
            case R.id.eight: {
                if (ifmoney) {
                    addKeyBoard("8");
                } else {
                    addKeyBoardFirst("8");
                }
                break;
            }
            case R.id.night: {
                if (ifmoney) {
                    addKeyBoard("9");
                } else {
                    addKeyBoardFirst("9");
                }
                break;
            }
            case R.id.zero: {
                if (ifmoney) {
                    addKeyBoard("0");
                } else {
                    addKeyBoardFirst("0");
                }
                break;
            }
            case R.id.other: {
                // if (ifmoney) {
                // addKeyBoard("00");
                // } else {
                // addKeyBoardFirst("00");
                // }

                // if(listener!=null){
                // listener.onKeyOther();
                // }
                isPlus = false;
                if (currInputNum.length() > 0) {
                    changeShouqianButton(2);
                    numList.add(currInputNum);
                    currInputNum = "";

                    // keyboardEdit.setText(keyboardEdit.getText() + "+");
                    StringBuffer buffer = new StringBuffer("");
                    for (int i = 0; i < numList.size(); i++) {
                        String str = numList.get(i);
                        if (i == numList.size() - 1) {
                            buffer.append(str + "+");
                            // currInputNum = str;
                        } else {
                            buffer.append(str + "+");
                        }
                    }
                    // numList.remove(numList.size() - 1);
                    System.out.println("bbbuffer:" + buffer.toString());
                    keyboardEdit.setText(buffer.toString());

                } else if (numList.size() > 0) {
                    StringBuffer buffer = new StringBuffer("");
                    for (int i = 0; i < numList.size(); i++) {
                        String str = numList.get(i);
                        if (i == numList.size() - 1) {
                            buffer.append(str + "+");
                            // currInputNum = str;
                        } else {
                            buffer.append(str + "+");
                        }
                    }
                    // numList.remove(numList.size() - 1);
                    keyboardEdit.setText(buffer.toString());
                    changeShouqianButton(2);
                }
                amountNumView.setText("");
                other.setSelected(true);
//			if (!keyboardEdit.isShowState()) {
//				keyboardEdit.isShowLine(true);
//			}

                break;
            }
            case R.id.shanchu: {
                isPlus = false;
                // 大于1数 存在加号的情况下
                if (numList.size() > 0) {
                    // 当前有输入新数字
                    if (currInputNum.length() > 0) {

                        if (currMode == 1) {
                            StringBuffer buffer = new StringBuffer("");
                            for (int i = 0; i < numList.size(); i++) {
                                String str = numList.get(i);
                                if (i == numList.size() - 1) {
                                    buffer.append(str);
                                    currInputNum = str;
                                } else {
                                    buffer.append(str + "+");
                                }
                            }
                            numList.remove(numList.size() - 1);
                            keyboardEdit.setText(buffer.toString());

                            changeShouqianButton(2);
                        } else {

                            StringBuffer buffer = new StringBuffer("");
                            for (int i = 0; i < numList.size(); i++) {
                                String str = numList.get(i);
                                if (i == numList.size() - 1) {
                                    buffer.append(str);
                                    currInputNum = str;
                                } else {
                                    buffer.append(str + "+");
                                }

                            }
                            numList.remove(numList.size() - 1);
                            keyboardEdit.setText(buffer.toString());

                        }
                    } else {// 无数字输入

                        if (currMode == 1) {// 已经按了=号 准备收钱
                            StringBuffer buffer = new StringBuffer("");
                            for (int i = 0; i < numList.size(); i++) {
                                String str = numList.get(i);
                                if (i == numList.size() - 1) {
                                    buffer.append(str);
                                    currInputNum = str;
                                } else {
                                    buffer.append(str + "+");
                                }
                            }
                            numList.remove(numList.size() - 1);
                            keyboardEdit.setText(buffer.toString());

                            changeShouqianButton(2);
                        } else {
                            StringBuffer buffer = new StringBuffer("");
                            // for (String str : numList) {
                            // buffer.append(str + "+");
                            // }
                            // if(numList.size()>1){
                            // currInputNum = numList.get(numList.size()-2);
                            // }
                            if (numList.size() > 1) { // 大于2个数的情况
                                for (int i = 0; i < numList.size() - 1; i++) {
                                    String str = numList.get(i);
                                    if (i == numList.size() - 2) {
                                        buffer.append(str);
                                        currInputNum = str;
                                    } else {
                                        buffer.append(str + "+");
                                    }

                                }
                                numList.remove(numList.size() - 1);
                            } else {// 剩一个数的情况

                                for (int i = 0; i < numList.size(); i++) {
                                    String str = numList.get(i);
                                    if (i == numList.size() - 1) {
                                        buffer.append(str);
                                        currInputNum = str;
                                    } else {
                                        buffer.append(str + "+");
                                    }
                                }
                            }
                            numList.remove(numList.size() - 1);
                            keyboardEdit.setText(buffer.toString());
                        }
                    }
                    // 只有一个数就收钱
                    if (numList.size() == 0) {
                        changeShouqianButton(1);
                    }
                }
                // else if(numList.size()==2){
                //
                // }
                else {
                    deleteKeyBoard();
                    // if (currInputNum.equals("")) {
                    // keyboardEdit.setText("0");
                    // }
                }
                amountNumView.setText("");

//			if (!keyboardEdit.isShowState()) {
//				keyboardEdit.isShowLine(true);
//			}

                break;
            }
            case R.id.dot: {
                addKeyBoardFirst(".");
                break;
            }
            case R.id.allClean: {
                // isPlus = false;
                // currInputNum = "";
                // keyboardEdit.setText("0");
                // numList.clear();
                // amountNumView.setText("");
                // changeShouqianButton(1);
                // amount = 0;
                resetKey();
                break;
            }
        }
    }

    public interface KeyOtherListenr {
        public void onKeySure(double amount);
    }

    public void changeShouqianButton(int type) {
        currMode = type;
        if (type == 1) {
            shouqian.setBackgroundResource(R.drawable.button_shouqian);
            shouqian.setText(activity.getString(R.string.cashier2));
//			shouqian.setTextSize(activity.getResources().getDimension(
//					R.dimen.keyboard_txt_size)
//					/ AppData.phone_density);
            // shouqian.setTextSize(activity.getResources().getDimension(
            // R.dimen.title_height2));
            shouqian.setOnClickListener(shouqianListener);
            other.setSelected(false);

            if (keyboardEdit != null && amountNumView != null) {
                // 收钱状态
                keyboardEdit.setTextColor(Color.rgb(0xff, 0xff, 0xff));
                amountNumView.setTextColor(Color.rgb(0xff, 0xff, 0xff));
            }
        } else {
            // ++++++++++
            // shouqian.setBackgroundResource(R.drawable.buttongreen_selectoe);
            shouqian.setText("=");
//			shouqian.setTextSize(activity.getResources().getDimension(
//					R.dimen.title_height));
            shouqian.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (currInputNum.length() > 0) {
                        numList.add(currInputNum);
                        currInputNum = "";
                    }

                    amount = 0;
                    for (String str : numList) {
                        // System.out.println("str:" + str);
                        amount += Double.parseDouble(str);
                    }

                    if (keyboardEdit
                            .getText()
                            .toString()
                            .substring(
                                    keyboardEdit.getText().toString().length() - 1,
                                    keyboardEdit.getText().toString().length())
                            .equals("+")) {
                        keyboardEdit.setText(keyboardEdit
                                .getText()
                                .toString()
                                .substring(
                                        0,
                                        keyboardEdit.getText().toString()
                                                .length() - 1)
                                + "=");
                    } else {
                        keyboardEdit.setText(keyboardEdit.getText() + "=");
                    }
                    // System.out.println("amount:" + amount);
                    if (amountNumView != null) {
                        amountNumView.setVisibility(View.VISIBLE);
                        keyboardEdit.setVisibility(View.GONE);
                        amountNumView.setText(String.format("%.02f", amount));

                        final TranslateAnimation anim = new TranslateAnimation(
                                Animation.RELATIVE_TO_SELF, 0,
                                Animation.RELATIVE_TO_SELF, 0,
                                Animation.RELATIVE_TO_SELF, 1,
                                Animation.RELATIVE_TO_SELF, 0);
                        anim.setDuration(500);
                        anim.setInterpolator(AnimationUtils
                                .loadInterpolator(
                                        activity,
                                        android.R.anim.accelerate_decelerate_interpolator));
                        amountNumView.startAnimation(anim);

                    }
                    if (line != null) {
                        lineIsOpen = false;
                        final TranslateAnimation anim = new TranslateAnimation(
                                Animation.RELATIVE_TO_SELF, 0,
                                Animation.RELATIVE_TO_SELF, 0,
                                Animation.RELATIVE_TO_SELF, -6,
                                Animation.RELATIVE_TO_SELF, 0);
                        anim.setDuration(500);
                        anim.setInterpolator(AnimationUtils
                                .loadInterpolator(
                                        activity,
                                        android.R.anim.accelerate_decelerate_interpolator));
                        line.startAnimation(anim);
                    }

                    isPlus = true;
                    changeShouqianButton(1);
                    keyboardEdit.isShowLine(false);
                }
            });
            if (keyboardEdit != null && amountNumView != null) {
                keyboardEdit.setTextColor(Color.rgb(0xff, 0xff, 0xff));
                amountNumView.setVisibility(View.GONE);
                keyboardEdit.setVisibility(View.VISIBLE);

                if (line != null && !lineIsOpen) {
                    lineIsOpen = true;
                    final TranslateAnimation anim = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0,
                            Animation.RELATIVE_TO_SELF, 0,
                            Animation.RELATIVE_TO_SELF, 6,
                            Animation.RELATIVE_TO_SELF, 0);
                    anim.setDuration(500);
                    anim.setInterpolator(AnimationUtils.loadInterpolator(
                            activity,
                            android.R.anim.accelerate_decelerate_interpolator));
                    line.startAnimation(anim);
                }

                // amountNumView.setTextColor(Color.rgb(0x33, 0x33, 0x33));
            }
        }
    }

    // public void setMode(int type){
    // currMode = type;
    // if(currMode==1){
    //
    // }else{
    //
    // }
    //
    // }

    private View.OnClickListener shouqianListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (numList.size() > 0) {
                if (amount > 0) {

                    // Toast.makeText(activity, amount + "",
                    // 3000).show();
                    if (listener != null) {
                        listener.onKeySure(amount);
                    }
                } else {
                    Toast.makeText(activity, activity.getString(R.string.please_enter_amt), 3000).show();
                }
            } else if (currInputNum.length() > 0
                    && Double.parseDouble(currInputNum) > 0) {
                if (listener != null) {
                    listener.onKeySure(Double.parseDouble(currInputNum));
                }
            } else {
                Toast.makeText(activity, activity.getString(R.string.please_enter_amt), 3000).show();
            }
        }
    };

    /**
     * 重置金额
     */
    public void resetKey() {
        isPlus = false;
        currInputNum = "";

        numList.clear();
        amountNumView.setText("");
        amountNumView.setVisibility(View.GONE);
        changeShouqianButton(1);
        amount = 0;
        lineIsOpen = true;
        keyboardEdit.setText("0");
//		keyboardEdit.isShowLine(true);
        if (keyboardEdit.getVisibility() != View.VISIBLE) {
            keyboardEdit.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 恢复状态
     */
    public void reState() {
        if (numList.size() > 0) {
            amountNumView.setVisibility(View.VISIBLE);
            keyboardEdit.setVisibility(View.GONE);
        } else {

        }
    }

    public void setTextSize(float size) {
        one.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        two.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        three.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        four.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        five.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        six.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        seven.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        eight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        night.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        zero.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        dot.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        addTxtView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
//		allClean.setTextSize(size);
//		shouqian.setTextSize(size);
        amountNumView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        keyboardEdit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
    }

    public void setAllClearTxtSize(float size) {
        allClean.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        shouqian.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
    }

}
