/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.utils;

import ovh.damianosdw.infernoapi.dbmodels.CandidatesVipActivityCheck;
import ovh.damianosdw.infernoapi.dbmodels.SpActivityCheck;
import ovh.damianosdw.infernoapi.dbmodels.VipActivityCheck;

public abstract class ChannelsActivityUtils
{
    public static int getPrivChannelActivity(int channelNumber, SpActivityCheck spActivity)
    {
        switch(channelNumber)
        {
            case 1:
                return spActivity.getSp1();
            case 2:
                return spActivity.getSp2();
            case 3:
                return spActivity.getSp3();
            case 4:
                return spActivity.getSp4();
            case 5:
                return spActivity.getSp5();
            case 6:
                return spActivity.getSp6();
            case 7:
                return spActivity.getSp7();
            case 8:
                return spActivity.getSp8();
            case 9:
                return spActivity.getSp9();
            case 10:
                return spActivity.getSp10();
            case 11:
                return spActivity.getSp11();
            case 12:
                return spActivity.getSp12();
            case 13:
                return spActivity.getSp13();
            case 14:
                return spActivity.getSp14();
            case 15:
                return spActivity.getSp15();
            case 16:
                return spActivity.getSp16();
            case 17:
                return spActivity.getSp17();
            case 18:
                return spActivity.getSp18();
            case 19:
                return spActivity.getSp19();
            case 20:
                return spActivity.getSp20();
            case 21:
                return spActivity.getSp21();
            case 22:
                return spActivity.getSp22();
            case 23:
                return spActivity.getSp23();
            case 24:
                return spActivity.getSp24();
            case 25:
                return spActivity.getSp25();
            case 26:
                return spActivity.getSp26();
            case 27:
                return spActivity.getSp27();
            case 28:
                return spActivity.getSp28();
            case 29:
                return spActivity.getSp29();
            case 30:
                return spActivity.getSp30();
            case 31:
                return spActivity.getSp31();
            case 32:
                return spActivity.getSp32();
            case 33:
                return spActivity.getSp33();
            case 34:
                return spActivity.getSp34();
            case 35:
                return spActivity.getSp35();
            case 36:
                return spActivity.getSp36();
            case 37:
                return spActivity.getSp37();
            case 38:
                return spActivity.getSp38();
            case 39:
                return spActivity.getSp39();
            case 40:
                return spActivity.getSp40();
            case 41:
                return spActivity.getSp41();
            case 42:
                return spActivity.getSp42();
            case 43:
                return spActivity.getSp43();
            case 44:
                return spActivity.getSp44();
            case 45:
                return spActivity.getSp45();
            case 46:
                return spActivity.getSp46();
            case 47:
                return spActivity.getSp47();
            case 48:
                return spActivity.getSp48();
            case 49:
                return spActivity.getSp49();
            case 50:
                return spActivity.getSp50();
            default:
                return 0;
        }
    }
    public static int getVipChannelActivity(int channelNumber, VipActivityCheck vipActivity)
    {
        switch(channelNumber)
        {
            case 1:
                return vipActivity.getVip1();
            case 2:
                return vipActivity.getVip2();
            case 3:
                return vipActivity.getVip3();
            case 4:
                return vipActivity.getVip4();
            case 5:
                return vipActivity.getVip5();
            case 6:
                return vipActivity.getVip6();
            case 7:
                return vipActivity.getVip7();
            case 8:
                return vipActivity.getVip8();
            case 9:
                return vipActivity.getVip9();
            case 10:
                return vipActivity.getVip10();
            case 11:
                return vipActivity.getVip11();
            case 12:
                return vipActivity.getVip12();
            case 13:
                return vipActivity.getVip13();
            case 14:
                return vipActivity.getVip14();
            case 15:
                return vipActivity.getVip15();
            case 16:
                return vipActivity.getVip16();
            case 17:
                return vipActivity.getVip17();
            case 18:
                return vipActivity.getVip18();
            case 19:
                return vipActivity.getVip19();
            case 20:
                return vipActivity.getVip20();
            case 21:
                return vipActivity.getVip21();
            case 22:
                return vipActivity.getVip22();
            case 23:
                return vipActivity.getVip23();
            case 24:
                return vipActivity.getVip24();
            case 25:
                return vipActivity.getVip25();
            case 26:
                return vipActivity.getVip26();
            case 27:
                return vipActivity.getVip27();
            case 28:
                return vipActivity.getVip28();
            case 29:
                return vipActivity.getVip29();
            case 30:
                return vipActivity.getVip30();
            default:
                return 0;
        }
    }
    public static int getCandidatesVipChannelActivity(int channelNumber, CandidatesVipActivityCheck vipActivity)
    {
        switch(channelNumber)
        {
            case 1:
                return vipActivity.getVip1();
            case 2:
                return vipActivity.getVip2();
            case 3:
                return vipActivity.getVip3();
            case 4:
                return vipActivity.getVip4();
            case 5:
                return vipActivity.getVip5();
            case 6:
                return vipActivity.getVip6();
            case 7:
                return vipActivity.getVip7();
            case 8:
                return vipActivity.getVip8();
            case 9:
                return vipActivity.getVip9();
            case 10:
                return vipActivity.getVip10();
            case 11:
                return vipActivity.getVip11();
            case 12:
                return vipActivity.getVip12();
            case 13:
                return vipActivity.getVip13();
            case 14:
                return vipActivity.getVip14();
            case 15:
                return vipActivity.getVip15();
            case 16:
                return vipActivity.getVip16();
            case 17:
                return vipActivity.getVip17();
            case 18:
                return vipActivity.getVip18();
            case 19:
                return vipActivity.getVip19();
            case 20:
                return vipActivity.getVip20();
            case 21:
                return vipActivity.getVip21();
            case 22:
                return vipActivity.getVip22();
            case 23:
                return vipActivity.getVip23();
            case 24:
                return vipActivity.getVip24();
            case 25:
                return vipActivity.getVip25();
            case 26:
                return vipActivity.getVip26();
            case 27:
                return vipActivity.getVip27();
            case 28:
                return vipActivity.getVip28();
            case 29:
                return vipActivity.getVip29();
            case 30:
                return vipActivity.getVip30();
            default:
                return 0;
        }
    }
}
