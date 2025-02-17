package com.zoser.app.powermote;

public class IRMessages
{
    // MISC
    public static IRMessage NEC_REPEAT;
    // Sony Speaker
    public static IRMessage HOME_SONY_SPEAKER_ON;

    // Sony HT
    public static IRMessage HOME_SONY_HT_ON;
    public static IRMessage HOME_SONY_HT_VOLUME_UP;
    public static IRMessage HOME_SONY_HT_VOLUME_DOWN;
    // HDMI SPLITTER
    public static IRMessage HDMI_SPLITTER_ON;
    public static IRMessage HDMI_SPLITTER_SET_1;
    public static IRMessage HDMI_SPLITTER_SET_2;
    public static IRMessage HDMI_SPLITTER_SET_3;
    public static IRMessage HDMI_SPLITTER_SET_4;
    public static IRMessage HDMI_SPLITTER_SET_5;

    // LG TV
    public static IRMessage HOME_LG_TV_ON;
    public static IRMessage HOME_LG_TV_VOLUME_UP;
    public static IRMessage HOME_LG_TV_VOLUME_DOWN;
    // Xiaomi TV
    public static IRMessage XIAOMI_TV_4S_1;
    public static IRMessage XIAOMI_TV_4S_2;
    public static IRMessage XIAOMI_TV_4S_3;
    public static IRMessage XIAOMI_TV_4S_MUTE;
    public static IRMessage XIAOMI_TV_4S_INPUT;
    public static IRMessage XIAOMI_TV_4S_4;
    public static IRMessage XIAOMI_TV_4S_5;
    public static IRMessage XIAOMI_TV_4S_6;
    public static IRMessage XIAOMI_TV_4S_7;
    public static IRMessage XIAOMI_TV_4S_8;
    public static IRMessage XIAOMI_TV_4S_9;
    public static IRMessage XIAOMI_TV_4S_0;
    public static IRMessage XIAOMI_TV_4S_FWD;
    public static IRMessage XIAOMI_TV_4S_REW;

    public static void initialize()
    {
        // MISC
        NEC_REPEAT = IRNecFactory.createRepeat();

        // Sony Speaker
        HOME_SONY_SPEAKER_ON = IRSonyFactory.create(IRSonyFactory.TYPE_12_BITS,84,1,3);

        // Sony HT
        HOME_SONY_HT_ON      = IRSonyFactory.create(IRSonyFactory.TYPE_15_BITS,84,10,3);
        HOME_SONY_HT_VOLUME_UP = IRSonyFactory.create(IRSonyFactory.TYPE_15_BITS,36,10,3);
        HOME_SONY_HT_VOLUME_DOWN = IRSonyFactory.create(IRSonyFactory.TYPE_15_BITS,100,10,3);

        // LG TV
        HOME_LG_TV_ON           = IRNecFactory.create(16,32,2);
        HOME_LG_TV_VOLUME_UP    = IRNecFactory.create(64,32,2);
        HOME_LG_TV_VOLUME_DOWN  = IRNecFactory.create(192,32,2);

        //HDMI SPLITTER
        HDMI_SPLITTER_ON    = IRNecFactory.create(98,0,0);
        HDMI_SPLITTER_SET_1 = IRNecFactory.create(2,0,0);
        HDMI_SPLITTER_SET_2 = IRNecFactory.create(224,0,0);
        HDMI_SPLITTER_SET_3 = IRNecFactory.create(168,0,0);
        HDMI_SPLITTER_SET_4 = IRNecFactory.create(144,0,0);
        HDMI_SPLITTER_SET_5 = IRNecFactory.create(152,0,0);

        int deviceCode = 0x86;

        XIAOMI_TV_4S_MUTE = IRNecFactory.create(0xa1, deviceCode, 2);
        XIAOMI_TV_4S_INPUT = IRNecFactory.create(0x01, deviceCode, 2);
        XIAOMI_TV_4S_FWD = IRNecFactory.create(0xa7, deviceCode, 2);
        XIAOMI_TV_4S_REW = IRNecFactory.create(0xa8, deviceCode, 2);
        XIAOMI_TV_4S_1 = IRNecFactory.create(0xc1, deviceCode, 2);
        XIAOMI_TV_4S_2 = IRNecFactory.create(0xc2, deviceCode, 2);
        XIAOMI_TV_4S_3 = IRNecFactory.create(0xc3, deviceCode, 2);
        XIAOMI_TV_4S_4 = IRNecFactory.create(0xc4, deviceCode, 2);
        XIAOMI_TV_4S_5 = IRNecFactory.create(0xc5, deviceCode, 2);
        XIAOMI_TV_4S_6 = IRNecFactory.create(0xc6, deviceCode, 2);
        XIAOMI_TV_4S_7 = IRNecFactory.create(0xc7, deviceCode, 2);
        XIAOMI_TV_4S_8 = IRNecFactory.create(0xc8, deviceCode, 2);
        XIAOMI_TV_4S_9 = IRNecFactory.create(0xc9, deviceCode, 2);
        XIAOMI_TV_4S_0 = IRNecFactory.create(0xca, deviceCode, 2);
        
    }
}
