package com.hassan.main.core.enumurations;

public enum ServerActionEnum {
    SAVE('S'),
    UPDATE('U'),
    DELETE('D');

    private Character action;

    private ServerActionEnum(Character _action){
        this.action = _action;
    }

    public Character getAction(){
        return this.action;
    }

    public static  ServerActionEnum getServerAction(Character _action){
        for(ServerActionEnum serverAction: ServerActionEnum.values()){
            if(serverAction.getAction().equals(_action)){
                return serverAction;
            }
        }
        throw new IllegalStateException("Unexpected Server Action value: " + _action);
    }
}
