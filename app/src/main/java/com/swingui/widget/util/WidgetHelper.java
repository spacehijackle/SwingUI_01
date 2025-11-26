package com.swingui.widget.util;

import java.awt.Window;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.swingui.widget.Framer;
import com.swingui.widget.Widget;

/**
 * {@link Widget} 関連のユーティリティ・クラス
 * 
 * @author t.yoshida
 */
public class WidgetHelper
{
    /**
     * ウィジェットが属するフレームまで遡り、下位コンポーネント全体の更新を行う。
     */
    public static void invokeToRefresh(Widget<?> widget)
    {
        if(widget instanceof JComponent == false) return;

        Window w = SwingUtilities.getWindowAncestor((JComponent)widget);
        if(w != null)
        {
            if(w instanceof Framer)
            {
                ((Framer)w).refreshWT();
            }
        }
    }
}
