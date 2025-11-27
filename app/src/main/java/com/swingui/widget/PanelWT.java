package com.swingui.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.swingui.value.UIValue;
import com.swingui.widget.util.WidgetHelper;

/**
 * {@link JPanel} を拡張した {@link Widget} 実装クラス
 * 
 * @author t.yoshida
 */
public class PanelWT extends JPanel implements Widget<PanelWT>
{
    // UIフォーカス状態
    private UIValue<Boolean> hasFocus = new UIValue<>(false);

    // 背景色
    private UIValue<Color> bgColor = new UIValue<>(getBackground());

    /**
     * {@code PanelWT} を生成する。
     */
    public PanelWT()
    {
        super();

        init();
    }

    /**
     * レイアウト・マネージャを指定して {@code PanelWT} を生成する。
     * 
     * @param manager レイアウト・マネージャ
     */
    public PanelWT(LayoutManager manager)
    {
        super(manager);

        init();
    }

    @Override
    public void dispose()
    {
        hasFocus = UIValue.of(null);
        bgColor = UIValue.of(null);
    }

    /**
     * 初期化処理
     */
    private void init()
    {
        installFocusListener();
    }

    /**
     * フォーカスの監視をする。
     */
    protected void installFocusListener()
    {
        addFocusListener(new FocusListener()
        {
            @Override public void focusLost(FocusEvent e) { hasFocus.set(false); }

            @Override public void focusGained(FocusEvent e) { }
        });
    }

    @Override
    public void refreshWT()
    {
        // 背景色設定
        setBackground(bgColor.get());

        // フォーカス取得
        if(hasFocus.get()) requestFocusInWindow();
    }

    @Override
    public PanelWT padding(int padding)
    {
        setBorder
        (
            BorderFactory.createCompoundBorder
            (
                getBorder(),
                BorderFactory.createEmptyBorder(padding, padding, padding, padding)
            )
        );

        return this;
    }

    @Override
    public PanelWT enabled(UIValue<Boolean> isEnabled)
    {
        return this;
    }

    @Override
    public PanelWT frame(int width, int height)
    {
        Dimension d = new Dimension(width, height);
        setPreferredSize(d);
        setMaximumSize(d);
        setMinimumSize(d);
        return this;
    }

    @Override
    public PanelWT focus(UIValue<Boolean> hasFocus)
    {
        this.hasFocus = hasFocus;
        this.hasFocus.addValueChangeListener(() -> WidgetHelper.invokeToRefresh(PanelWT.this));
        if(hasFocus.get()) requestFocusInWindow();
        return this;
    }

    @Override
    public PanelWT background(UIValue<Color> bgColor)
    {
        this.bgColor = bgColor;
        this.bgColor.addValueChangeListener(() -> WidgetHelper.invokeToRefresh(PanelWT.this));
        setBackground(bgColor.get());
        return this;
    }

    @Override
    public PanelWT self(Consumer<PanelWT> self)
    {
        self.accept(this);
        return this;
    }
}
