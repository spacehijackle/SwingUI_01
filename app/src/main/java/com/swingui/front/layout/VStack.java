package com.swingui.front.layout;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

import com.swingui.constant.UIAlignmentX;
import com.swingui.constant.UIDefaults;
import com.swingui.widget.PanelWT;

/**
 * コンポーネントを縦に並べるレイアウト提供クラス
 * 
 * @author t.yoshida
 */
public class VStack
{
    /**
     * 指定されたコンポーネントを縦に並べる。
     *
     * @param components 並べるコンポーネント群
     * @return {@link PanelWT}
     */
    public static PanelWT of(JComponent... components)
    {
        return init(UIAlignmentX.Center, components);
    }

    /**
     * 水平方向の位置指定をし、指定されたコンポーネントを縦に並べる。
     *
     * @param alignmentX 水平方向の位置指定
     * @param components 並べるコンポーネント群
     * @return {@link PanelWT}
     */
    public static PanelWT of(UIAlignmentX alignmentX, JComponent... components)
    {
        return init(alignmentX, components);
    }

    /**
     * 初期化処理
     * 
     * @param alignmentX 水平方向の位置指定
     * @param components 並べるコンポーネント群
     * @return {@link PanelWT}
     */
    private static PanelWT init(UIAlignmentX alignmentX, JComponent... components)
    {
        PanelWT panel = new PanelWT();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createGlue());  // フレームが広がった場合の上部調整用スペース
        for(int i=0; i<components.length; i++)
        {
            JComponent c = components[i];

            c.setAlignmentX(alignmentX.value);
            c.setAlignmentY(Component.CENTER_ALIGNMENT);
            //panel.add(Box.createGlue());  // フレームが広がった場合のコンポーネント間調整用スペース
            panel.add(c);

            boolean isTail = (i == (components.length - 1));
            if(!isTail)
            {
                panel.add(Box.createRigidArea(new Dimension(0, UIDefaults.COMPONENT_GAP)));
            }
        }
        panel.add(Box.createGlue());  // フレームが広がった場合の下部調整用スペース

        return panel;
    }
}