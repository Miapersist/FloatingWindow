package dongzhong.testforfloatingwindow;

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.graphics.Color.BLUE;

/**
 * Created by dongzhong on 2018/5/30.
 */

public class FloatingButtonService extends Service {
    public static boolean isStarted = false;

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;

    private Button button;
    public static int states = 0; //字体隐藏状态


    @Override
    public void onCreate() {
        super.onCreate();
        isStarted = true;
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        layoutParams = new WindowManager.LayoutParams();

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int wid = dm.widthPixels;
        int hei = dm.heightPixels;




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            //layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        /**
         * FLAG_NOT_TOUCH_MODAL
         窗口标志：即使该窗口是可对焦的（其#FLAG_NOT_FOCUSABLE未设置），
         允许窗口外的任何指针事件发送到其后面的窗口。
         否则它将消耗所有指针事件本身，而不管它们是否在窗口内。
         */
        /**
         窗口标志：此窗口不会获得关键输入焦点，因此用户无法向其发送键或其他按钮事件。
         那些会改变它背后的任何可关注的窗口。
         此标志还将启用#FLAG_NOT_TOUCH_MODAL是否显式设置。
         *   FLAG_NOT_FOCUSABLE
         设置此标志也意味着该窗口不需要与软输入法进行交互，
         因此它将被Z-排序并且与任何活动输入法无关（通常这意味着它在输入法之上得到Z-排序，
         所以它可以使用全屏的内容，如果需要的话可以覆盖输入法。
         可以使用{@link #FLAG_ALT_FOCUSABLE_IM}来修改这个行为。
         * */

        layoutParams.width = wid/5;
        layoutParams.height = hei/9;
        layoutParams.x = 0;
        layoutParams.y = 0;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showFloatingWindow();
        return super.onStartCommand(intent, flags, startId);
    }

    private void showFloatingWindow() {
        if (Settings.canDrawOverlays(this)) {
            //TextView textview = new TextView(getApplicationContext());
            //textview.setMovementMethod(ScrollingMovementMethod.getInstance());
            //textview.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10001)});
//            textview.setText("夕阳西下，大地沐浴在余辉的彩霞中，人们三三两两地在街道上漫步，晚风徐徐地拂送来一阵阵花木夹杂的幽香，使人心旷神怡，更觉夕阳无限好。 风儿吹走了我一整天的烦恼，我情不自禁地低吟浅唱着。这时给以我的不是痛苦和伤悲，而是一种艺术上的悲感，这份悲感并非悲哀的感觉，它是一种无与伦比而悲壮的心情，当你向西远眺时，那熔金般的烈焰，渐渐的由绚烂归于平淡，那一瞬间，一种温馨的感觉便会从心中油然而生。 一抹殷红色的夕阳照在西山上，湛蓝湛蓝的天空浮动着大块大块的白色云朵，它们在夕阳的辉映下呈现出火焰一般的嫣红，倘若你仔细地看，你会看见那云絮在空中飘动，就像置身于轻纱般的美梦似的，会使你远离烦恼的困扰。 我在校园的操场上惬意地漫步着，步子放得那么的轻，那么的慢，似乎不愿意去打扰这醉人的黄昏。 疲惫了一整天的眼睛，在这时候，瞩目西方，只见斜阳已经衔着山巅了，残阳如血，霞云似火，给校园、树林、河流、村庄、山峦镀上了柔和的胭脂红色，周围还放射着淡淡的金光。 池鱼归渊，炊烟唤子，客旅兼程。夕阳吻地的轻响，划分了白天与黑夜。于是投林的倦鸟，也便如诗人焚烧的诗稿，载着夕阳的殷殷血焰归去了…… 一阵清爽的夜风扑面而来，仿佛一切烦恼与疲惫都置之度外了，身体的每一根紧张的神经也渐渐舒缓了；风儿吹皱的河面，泛起了层层涟漪，折射着殷红的霞光，像撒下一河红色的玛瑙，熠熠生辉；远处的小竹林闪着绿幽幽的光，在微风中轻轻摇响竹叶，风儿吹动树叶那飒飒作响的声音，像唱着一首动听的歌；高空的风，恣意地追逐着、戏弄着，撕扯着云朵。 再往下看，一条小河—小北江，竹子的色调和晚霞的红晕，使小北江又增添了一份静谧的气氛，而竹子那风度翩翩的倩影和晚霞那瑰丽似锦的光芒，又使小北江在人们心目中倍增亲切；几条小渔船归航了，在河面划开了一道波光粼粼的水纹。小鸟时而在半空中飞过，时而能听见喜鹊那清脆悦耳的鸣叫，这时，我才从黄昏的梦中苏醒过来。太阳落山了，燃烧着的晚霞也渐渐暗淡下来了。转眼间，西天的最后一抹晚霞已经融进冥冥的暮色之中，天色逐渐暗下来了，四周的群山，呈现出青黛色的轮廓，暮色渐浓，大地一片混沌迷茫。这宛如一首交响曲的尾音，优美极了，但却渐渐地归于岑寂、无声，引起人们心中无穷的感喟，给校园的黄昏铺上一层感人肺腑的诗意：“大漠孤烟直，长河落日圆”是它无与伦比的磅礴气势;“一道残阳铺水中，半江瑟瑟半江红”是它成熟的风韵;“人间重晚情”更衬出了它的宁静……渐渐的，渐渐的，夜幕降临了，我的脑海中还浮现着那醉人的黄昏，那美丽而令人心驰神往的情景深深地吸引着我：我的视线、我的精神、我的思想……全都被这美得难以形容的“黄昏图”所沉浸了，我陷入了这种莫名其妙的感觉中，不能自拔。我怀着恋恋不舍的心情，迈着沉重的脚步，静静地离开了这黄昏的边界……");
//            windowManager.addView(textview,layoutParams);
    //        textview.setOnTouchListener(new FloatingOnTouchListener());
//

            button = new Button(getApplicationContext());
            //button.setBackground();
            Drawable drawable = button.getBackground();
            button.setBackground(drawable);
            button.setTextColor(Color.WHITE);
            //button.setBackgroundColor(drawable);
            //button.setBackgroundDrawable(drawable);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(states == 0){
                        button.setText("Floating Window");
                        states++;
                    }else{
                        button.setText("");
                        states--;
                    }
                }
            });

            button.setBackgroundColor(Color.BLUE);
            windowManager.addView(button, layoutParams);


//            button.setOnTouchListener(new FloatingOnTouchListener());
        }
    }

    private class FloatingListener implements View.OnTouchListener
    {

        @Override
        public boolean onTouch(View v, MotionEvent event) {


            return false;
        }
    }

    private class FloatingOnTouchListener implements View.OnTouchListener {
        private int x;
        private int y;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = (int) event.getRawX();
                    y = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int nowX = (int) event.getRawX();
                    int nowY = (int) event.getRawY();
                    int movedX = nowX - x;
                    int movedY = nowY - y;
                    x = nowX;
                    y = nowY;
                    layoutParams.x = layoutParams.x + movedX;
                    layoutParams.y = layoutParams.y + movedY;
                    windowManager.updateViewLayout(view, layoutParams);
                    break;
                default:
                    break;
            }
            return false;
        }
    }
}
