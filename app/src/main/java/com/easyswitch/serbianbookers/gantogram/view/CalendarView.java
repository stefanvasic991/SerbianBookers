package com.easyswitch.serbianbookers.gantogram.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.gantogram.model.Milestone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.format.DateTimeFormat;

import java.util.List;

public class CalendarView extends View {
    private static final String TAG = CalendarView.class.getSimpleName();
    private DateTime minDate;
    private DateTime maxDate;
//    private List<Milestone> milestones;

    private Paint mCharPaint;
    private Paint mWeekSize;
    private Paint mCharTodayPaint;
    private Paint mTodayPaint;
    private Paint mCharMilestonePaint;
    private Paint mMilestonePaint;
    private Paint mTextPaint;
    private Paint mFillPaint;
    private Paint mLinePaint;
    private Paint mLineMonth;

    private Rect textBounds;

    private float scale;
    private int nx;
    private int dx;

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scale = getContext().getResources().getDisplayMetrics().density;

        mCharPaint = new Paint();
        mCharPaint.setAntiAlias(true);
        mCharPaint.setColor(getResources().getColor(R.color.date_text));
        mCharPaint.setTextSize(60f);

        mWeekSize = new Paint();
        mWeekSize.setAntiAlias(true);
        mWeekSize.setColor(getResources().getColor(R.color.date_text));
        mWeekSize.setTextSize(45f);

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLUE);

        mLineMonth = new Paint();
        mLineMonth.setColor(Color.WHITE);

        mTodayPaint = new Paint();
        mTodayPaint.setColor(ContextCompat.getColor(context, R.color.today_line));
        mTodayPaint.setStrokeWidth(10f * scale);

        mTextPaint = new Paint();

        mMilestonePaint = new Paint();
        mMilestonePaint.setColor(ContextCompat.getColor(context, R.color.milestone_line));
        mMilestonePaint.setStrokeWidth(3.333333333f * scale);

        textBounds = new Rect();

        mCharMilestonePaint = new Paint();
        mCharMilestonePaint.setAntiAlias(true);
        mCharMilestonePaint.setColor(ContextCompat.getColor(context, R.color.bar_text));
        mCharMilestonePaint.setTextSize(10.f * scale);
        mCharMilestonePaint.setTextAlign(Paint.Align.CENTER);

        mCharTodayPaint = new Paint();
        mCharTodayPaint.setAntiAlias(true);
        mCharTodayPaint.setColor(ContextCompat.getColor(context, R.color.bar_text));
        mCharTodayPaint.setTextSize(10.f * scale);
        mCharTodayPaint.setTextAlign(Paint.Align.CENTER);

        mFillPaint = new Paint();
        mFillPaint.setColor(ContextCompat.getColor(context, R.color.scale_dayoff));
    }

    public void setRange(DateTime minDate, DateTime maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public void setXAxis(int nx, int dx) {
        this.nx = nx;
        this.dx = dx;
    }

//    public void setMilestones(List<Milestone> milestones) {
//        this.milestones = milestones;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.rgb(255, 255, 255));
        int height = canvas.getHeight();

        /**
         * FOR Table Headers
         */

        for (int i = 0; i < nx; i++) {
            int dayOfMonth = minDate.getDayOfMonth();
            String dayOfWeek = minDate.dayOfWeek().getAsString();

            // Drawing the month & year
            if (dayOfMonth == 1 || dayOfMonth == 8 || dayOfMonth == 16 || dayOfMonth == 24) {
                canvas.drawText(minDate.monthOfYear().getAsShortText() + ", " + minDate.year().getAsShortText(), dx * i + 3 * scale, height / 2 - 10 * scale, mCharPaint);
                canvas.drawLine(dx * i, 0, dx * i, height, mLinePaint);
            }

            //Draw the day
            if (dayOfMonth <= 9) {
                canvas.drawText("  0" + dayOfMonth, dx * i, height - 60, mCharPaint);
                canvas.drawLine(dx * i, 0, dx * i, height, mLinePaint);
                canvas.drawLine(dx * i, 0, dx * i, height / 2 - 5, mLineMonth);
            } else {
                canvas.drawText("  " + dayOfMonth, dx * i, height - 60, mCharPaint);
                canvas.drawLine(dx * i, 0, dx * i, height, mLinePaint);
                canvas.drawLine(dx * i, 0, dx * i, height / 2 - 5, mLineMonth);
            }

            if (dayOfMonth == 1) {
                canvas.drawLine(dx * i, 0, dx * i, height, mLinePaint);
            }


            //Drawing the day of week
            if (dayOfWeek == "1") {
                dayOfWeek = "PO";
                canvas.drawText("   " + dayOfWeek, dx * i, height - 10, mWeekSize);
            }
            if (dayOfWeek == "2") {
                dayOfWeek = "UT";
                canvas.drawText("   " + dayOfWeek, dx * i, height - 10, mWeekSize);
            }
            if (dayOfWeek == "3") {
                dayOfWeek = "SR";
                canvas.drawText("   " + dayOfWeek, dx * i, height - 10, mWeekSize);
            }
            if (dayOfWeek == "4") {
                dayOfWeek = "ČE";
                canvas.drawText("   " + dayOfWeek, dx * i, height - 10, mWeekSize);
            }
            if (dayOfWeek == "5") {
                dayOfWeek = "PE";
                canvas.drawText("   " + dayOfWeek, dx * i, height - 10, mWeekSize);
            }
            if (dayOfWeek == "6") {
                dayOfWeek = "SU";
                canvas.drawText("   " + dayOfWeek, dx * i, height - 10, mWeekSize);
            }
            if (dayOfWeek == "7") {
                dayOfWeek = "NE";
                canvas.drawText("   " + dayOfWeek, dx * i, height - 10, mWeekSize);
            }

            /**
             * drawLine StartX, StartY,  StopX, StopY, color
             */
            canvas.drawLine(dx * (i - 1), height / 2 - 5 , dx * (i + 6), height / 2 - 5, mLinePaint);
            canvas.drawLine(dx * (i - 1), height - 3 , dx * (i + 6), height - 3, mLinePaint);

            // Separator line
            if (dayOfMonth == 1) {
                /**
                 * FOR Vertical Line
                 */
                canvas.drawLine(dx * i, height, dx * i, height, mLinePaint);
                /**
                 * FOR Horizontal Line
                 */
                canvas.drawLine(dx * i, height, dx * (i + 1) - 1, height, mLinePaint);
            }

//            if (milestones != null && !milestones.isEmpty()) {
//                for (Milestone milestone : milestones) {
//                    DateTime milestoneDeadline = DateTime.parse(milestone.getDeadline(), DateTimeFormat.forPattern("yyyy-MM-dd"));
//                    if (DateTimeComparator.getDateOnlyInstance().compare(milestoneDeadline, minDate) == 0) {
//                        String title = milestone.getTitle();
//                        canvas.drawLine(dx * i, 0, dx * i, height, mMilestonePaint);
//                        canvas.drawRect(dx * i, 0, dx * (i + (0.823529412f * title.length())), height, mMilestonePaint);
//                        canvas.drawRect(dx * i, 0, dx * (i + (14f)), height / 2, mMilestonePaint);
//                        canvas.drawText(milestone.getTitle(), dx * (i + 7f), height / 2 - 3 * scale, mCharMilestonePaint);
//                    }
//                }
//            }

            minDate = minDate.plusDays(1);
        }
    }
}
