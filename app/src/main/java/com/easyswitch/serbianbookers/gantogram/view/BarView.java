package com.easyswitch.serbianbookers.gantogram.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.gantogram.model.Task;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;

public class BarView extends View {
    private static final String TAG = BarView.class.getSimpleName();
    Context context;
    private float scale;
    private Task task;

    private Paint mFillDone = null;
    private Paint mFillUndone = null;
    private Paint mFillStatus = null;
    private Paint mBorderPaint = null;
    private Paint mStatusBorder = null;
    private Paint mCharTaskNamePaint = null;
//    private Paint shadow = null;

    private DateTime minDate;
    private DateTime maxDate;
    private DateTime start;
    private DateTime end;
    private DateTime statusUpdateDate;

    private int dx;
    private int dy;

    private int length;
    private int statusBarLength;
    private Float statusLength;
    private RectF rect;
    private RectF rectStatus;
    private RectF rectDone;
    private RectF shadowRect;
    private RectF borderRect;

    private int startX;
    private float startY;
    private int endX;
    private int statusEndX;
    private float endY;

    private int diff = 5;
    private int cornerRadius = 25;

    private BlurMaskFilter blur;
    private Boolean isTaskOrStatus;
    private String textStatus = "";

    public BarView(Context context) {
        this(context, null);
    }

    public BarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scale = getContext().getResources().getDisplayMetrics().density;
        this.context = context;
        setWillNotDraw(false);

        mFillDone = new Paint();
        mFillDone.setColor(ContextCompat.getColor(context, R.color.bar_done));

        mFillUndone = new Paint();
        mFillUndone.setColor(ContextCompat.getColor(context, R.color.bar_undone));

        mFillStatus = new Paint();

        mStatusBorder = new Paint();
        mStatusBorder.setStyle(Paint.Style.STROKE);
        mStatusBorder.setStrokeWidth(5);

        // stroke
        mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setColor(ContextCompat.getColor(context, R.color.bar_border));
        mBorderPaint.setStrokeWidth(5);

        mCharTaskNamePaint = new Paint();
        mCharTaskNamePaint.setAntiAlias(true);
        mCharTaskNamePaint.setColor(ContextCompat.getColor(context, R.color.bar_text));
        mCharTaskNamePaint.setTextSize(10.f * scale);
        mCharTaskNamePaint.setTextAlign(Paint.Align.CENTER);

//        shadow = new Paint();
//        shadow.setAntiAlias(true);
//        shadow.setColor(ContextCompat.getColor(context, R.color.bar_shadow));
//
//        blur = new BlurMaskFilter(10.0f, BlurMaskFilter.Blur.OUTER);
//        shadow.setMaskFilter(blur);
    }

    public void setRange(DateTime minDate, DateTime maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public void setDimen(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setTaskAndStatus(Boolean isTaskOrStatus, Task task) {
        this.task = task;
        this.isTaskOrStatus = isTaskOrStatus;
        start = DateTime.parse(task.getStartDate(), DateTimeFormat.forPattern("yyyy-MM-dd"));
        end = DateTime.parse(task.getEndDate(), DateTimeFormat.forPattern("yyyy-MM-dd"));

        Duration minDate_start = new Duration(minDate, start);
        int dis_start = (int) minDate_start.getStandardDays();

        Duration start_end = new Duration(start, end);
        length = ((int) start_end.getStandardDays() + 1) * dx;

        if (task != null && task.getStatusDate() != null) {
            Log.e(TAG, "STATUS UPDATE DATE: " + task.getStatusDate());
            statusUpdateDate = DateTime.parse(task.getStatusDate(), DateTimeFormat.forPattern("yyyy-MM-dd"));
            Duration status_start_end = new Duration(start, statusUpdateDate);
            statusBarLength = ((int) status_start_end.getStandardDays() + 1) * dx;        }

        String taskStatus = task.getPercentageComplete();
        float status = 0;
        try {
            status = Float.parseFloat(taskStatus);
        } catch (Exception e) {
            status = 0;
//            e.printStackTrace();
        }
        statusLength = length * (status / 100);

        startX = dis_start * dx;
        startY = dy / 4.2f;
        endX = startX + length;
        endY = dy / 2.7f * 2;
        statusEndX = startX + statusBarLength;

        rect = new RectF(startX, startY, endX, endY);
        rectStatus = new RectF(startX, startY, statusEndX, endY);
        rectDone = new RectF(startX, startY, startX + statusLength, endY);
        shadowRect = new RectF(startX + diff, startY + diff, endX + diff, endY + diff);
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        if (isTaskOrStatus) {
////        canvas.drawRect(shadowRect, shadow);
//            canvas.drawRoundRect(rect, cornerRadius, cornerRadius, mFillUndone);
//            canvas.drawRoundRect(rect, cornerRadius, cornerRadius, mBorderPaint);
//            canvas.drawRoundRect(rectDone, cornerRadius, cornerRadius, mFillDone);
//            int numOfChars = mCharTaskNamePaint.breakText(task.getTitle(), true, rect.width(), null);
////        int start = (task.getTitle().length() - numOfChars) / 2;
//            int start = 0;
//            canvas.drawText(task.getTitle(), start, start + numOfChars, rect.centerX(), rect.centerY() + 10, mCharTaskNamePaint);
//        } else if (task.getStatus() != null && !task.getStatus().isEmpty()) {
//            canvas.drawRoundRect(rectStatus, cornerRadius, cornerRadius, mFillStatus);
//            canvas.drawRoundRect(rectStatus, cornerRadius, cornerRadius, mStatusBorder);
//            int numOfChars = mCharTaskNamePaint.breakText(textStatus, true, rectStatus.width(), null);
////        int start = (task.getTitle().length() - numOfChars) / 2;
//            int start = 0;
//            canvas.drawText(textStatus, start, start + numOfChars, rectStatus.centerX(), rectStatus.centerY() + 10, mCharTaskNamePaint);
//        }
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(widthSize, heightSize);
    }
}
