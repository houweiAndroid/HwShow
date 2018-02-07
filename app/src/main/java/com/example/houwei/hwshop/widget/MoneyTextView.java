package com.example.houwei.hwshop.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;


import com.example.houwei.hwshop.R;


public class MoneyTextView extends AppCompatTextView {
	private int mWidth;
	private int offset;
	private float offsetTop;
	private int textWidth;
	private float density;
	private Paint paint;
	private boolean isShowLine;
	private boolean isNeedDraw;
	private OnMeasureListener listener;

	public MoneyTextView(Context context) {
		super(context);
		init(context);
	}

	public MoneyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// TypedArray a = context.obtainStyledAttributes(attrs,new
		// int[]{android.R.attr.textColor});
		// int color = a.getColor(android.R.attr.textColor, 0xff999999);
		// System.out.println("color:"+color);
		// a.recycle();
		//
		// LayoutNum = a.getInteger(R.styleable.MyView_LayoutNum, 3);
		// a.recycle();
		init(context);
	}

	private void init(Context context) {

		DisplayMetrics dm = new DisplayMetrics();
		WindowManager m = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		m.getDefaultDisplay().getMetrics(dm);
		density = dm.density;

		// final float density = Util.getPhoneDensity(context);
		offset = (int) (15 * density);
		offsetTop = (int) (2 * density);
		// this.setBackgroundColor(Color.GREEN);
		// getPaint().setColor(Color.rgb(0x99, 0x99, 0x99));
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);

		isShowLine = true;
	}

	public void isShowLine(boolean show) {
		this.isShowLine = show;
		postInvalidate();
	}
	
	public boolean isShowState(){
		return this.isShowLine;
	}

	public void setTextColor(int color) {
		getPaint().setColor(color);

	}

	@Override
	protected void onTextChanged(CharSequence text, int start,
                                 int lengthBefore, int lengthAfter) {
		requestLayout();
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		this.mWidth = this.getMeasuredWidth();
		textWidth = mWidth - (int) (60 * density);
		FontMetrics fm = this.getPaint().getFontMetrics();

		final float baseline = fm.descent - fm.ascent;

		int lenght = autoSpit(getText().toString(), getPaint(), 300).length;

		// System.out.println("lllllllllll:"+lenght);
		this.setMeasuredDimension(
				widthMeasureSpec,
				(int) Math.ceil((this.getPaddingTop() + (baseline + fm.leading)
						* lenght)));

		if (listener != null) {
			listener.onMeasureOk();
		}

		// int width =
		// mWidth-offset-this.getPaddingLeft()-this.getPaddingRight();
		// final float textWidth = getPaint().measureText(getText().toString());
		// int lines = (int) Math.ceil(textWidth / width);
		// System.out.println("height:"+this.getMeasuredHeight()+"    textHeight:"+Math.ceil((this.getPaddingTop()+(baseline+fm.leading)*lines)));
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		if (changed) {
			if (listener != null) {
				listener.onMeasureOk();
			}
		}
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// super.onDraw(canvas);
		// System.out.println("ondraw");

		FontMetrics fm = this.getPaint().getFontMetrics();

		final float baseline = fm.descent - fm.ascent;
		final float x = this.getPaddingLeft();
		float y = this.getPaddingTop() + baseline - offsetTop;
		final String txt = getText().toString();
		if (txt != null) {
			final int width = mWidth - this.getPaddingLeft()
					- this.getPaddingRight();
			String text[] = autoSpit(txt, getPaint(), width);
			for (final String str : text) {
				if (str != null) {
					float offsetWidth=getPaint().measureText(str);
					canvas.drawText(str, x+getMeasuredWidth()-offsetWidth, y, getPaint());
					y += baseline + fm.leading;
				}
			}

			if (isShowLine) {
				if (isNeedDraw) {
					y = (text.length - 1) * baseline;
					final float textWidth = getPaint().measureText(
							text[text.length - 1]);
//					Util.printMsg("width", textWidth + "   " + y + "   "
//							+ this.getPaint().getTextSize());

					if (getContext().getResources().getDimension(
							R.dimen.title_height) == this.getPaint()
							.getTextSize()) {
						y += 30;
					} else {
						y += 25;
					}

					canvas.drawLine(textWidth + 30, y, textWidth + 30, y
							+ baseline, paint);
					
				} 
				removeCallbacks(reDraw);
				postDelayed(reDraw, 500);
			}
		}

	}
	
	private Runnable reDraw =new Runnable(){

		@Override
		public void run() {
			
			isNeedDraw=!isNeedDraw;
			postInvalidate();
		}
		
	};

	private String[] autoSpit(String str, Paint p, int width) {

		// width -= offset;
		// final int contentLenght = str.length();
		// final float textWidth = p.measureText(str);
		// if (textWidth < width) {
		// return new String[] { str };
		// }
		// int start = 0, end = 1, i = 0;
		// int lines = (int) Math.ceil(textWidth / width);

		float amount = 0;
		// int length= 0;
		int nStart = 0;

		StringBuffer buffer = new StringBuffer("");
		String num[] = str.split("\\+");
		// System.out.println("kkk:"+num.length);

		for (int j = 0; j < num.length; j++) {
			amount += p.measureText(num[j] + "+");
			if (amount > textWidth && j != 0) {
				j--;
				// length++;

				for (int k = nStart; k <= j; k++) {
					// buffer.append(num[k]);
					// if(k!=j){
					// buffer.append("+");
					// }

					if (k != 0) {
						buffer.append("+");
					}
					buffer.append(num[k]);
				}
				amount = 0;
				buffer.append(",");
				nStart = j + 1;
			}

			if (j == num.length - 1) {
				for (int k = nStart; k <= j; k++) {
					// buffer.append(num[k]);
					// if(k!=j){
					// buffer.append("+");
					// }
					if (k != 0) {
						buffer.append("+");
					}
					buffer.append(num[k]);
				}
			}
		}
		if (str.substring(str.length() - 1, str.length()).equals("+")) {
			buffer.append("+");
		}

		// System.out.println(buffer.toString());

		return buffer.toString().split(",");

		// System.out.println("amount:"+buffer.toString());
		// final String texts[] = new String[lines];
		// while (start < contentLenght) {
		// if (p.measureText(str, start, end) > width) {
		// texts[i++] = str.substring(start, end);
		// start = end;
		// }
		// if (end == contentLenght) {
		// texts[i] = str.substring(start, end);
		// break;
		// }
		// end += 1;
		// }
		// return texts;

	}

	public void setListener(OnMeasureListener listener) {
		this.listener = listener;
	}

	public interface OnMeasureListener {
		public void onMeasureOk();
	}
}
