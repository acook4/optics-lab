package opticslab.events;

import opticslab.BasicMod;
import opticslab.cards.RayOfLight;
import opticslab.cards.dispersions.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class OpticsLab extends AbstractImageEvent {
    //This isn't technically needed but it becomes useful later
    public static final String ID = BasicMod.makeID("OpticsLab");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String NAME = eventStrings.NAME;

    public OpticsLab(){
        super(NAME, DESCRIPTIONS[0], "opticslab/events/opticsLab.jpg");

        //This is where you would create your dialog options
        imageEventText.setDialogOption(OPTIONS[0]); //Curiosity
        imageEventText.setDialogOption(OPTIONS[1]); //Refuse to help
    }

    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON)
            CardCrawlGame.sound.play("EVENT_LAB");
    }

    protected AbstractCard shouldUpgrade(AbstractCard card) {
        if(AbstractDungeon.player.hasRelic("PrismaticShard"))
        {
            card.upgrade();
        }
        return card;
    }

    @Override
    protected void buttonEffect(int i) { // This is the event:
        switch (screenNum) {
            case 0: // While you are on screen number 0 (The starting screen)
                if (i == 0) //choose to remove, change dialog
                {

                    AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, OPTIONS[5], false, false, false, true);
                    if (Settings.AMBIANCE_ON)
                        CardCrawlGame.sound.play("EVENT_SHINING");
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.updateDialogOption(0, OPTIONS[2]); //Spectrum
                    this.imageEventText.updateDialogOption(1, OPTIONS[3], (AbstractCard)new RayOfLight()); //Singular Ray

                    screenNum = 1;
                }
                else //decline, change dialog
                {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.updateDialogOption(0, OPTIONS[4]);

                    screenNum = 3;
                }
                break;
            case 1: // While you are on screen number 1 (Select amount of dispersions screen)
                String lightColour = "#p~";
                String terminator = "~";
                if (i == 0) //All seven
                {

                    AbstractCard rCard = new DispersionR();
                    AbstractCard yCard = new DispersionY();
                    AbstractCard iCard = new DispersionI();
                    AbstractCard gCard = new DispersionG();
                    AbstractCard oCard = new DispersionO();
                    AbstractCard bCard = new DispersionB();
                    AbstractCard vCard = new DispersionV();

                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(shouldUpgrade(rCard).makeStatEquivalentCopy(), (float) Settings.WIDTH / 2.0F - 570.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F));
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(shouldUpgrade(oCard).makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F - 380.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F));
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(shouldUpgrade(yCard).makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F - 190.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F));
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(shouldUpgrade(gCard).makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(shouldUpgrade(bCard).makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F + 190.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F));
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(shouldUpgrade(iCard).makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F + 380.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F));
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(shouldUpgrade(vCard).makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F + 570.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F));

                }
                else //Just one
                {

                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(shouldUpgrade(new RayOfLight()).makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    /* Old version where one random Dispersion would be obtained
                    int outcome = AbstractDungeon.miscRng.random(0, 6);
                    AbstractCard cardOutcome = null;
                    switch(outcome)
                    {
                        case 0:
                            lightColour = "#r~";
                            cardOutcome = new DispersionR();
                            break;
                        case 1:
                            lightColour = "~[#ffa500]";
                            terminator = "[]~";
                            cardOutcome = new DispersionO();
                            break;
                        case 2:
                            lightColour = "#y~";
                            cardOutcome = new DispersionY();
                            break;
                        case 3:
                            lightColour = "#g~";
                            cardOutcome = new DispersionG();
                            break;
                        case 4:
                            lightColour = "#b~";
                            cardOutcome = new DispersionB();
                            break;
                        case 5:
                            lightColour = "#p~";
                            cardOutcome = new DispersionI();
                            break;
                        case 6:
                            lightColour = "~[#6500B0]";
                            terminator = "[]~";
                            cardOutcome = new DispersionV();
                            break;
                        default:
                            break;
                    }

                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(shouldUpgrade(cardOutcome).makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));

                     */
                }

                String descriptionCalc = DESCRIPTIONS[3] + lightColour + DESCRIPTIONS[6] + terminator + DESCRIPTIONS[4] + lightColour + DESCRIPTIONS[6] + terminator + DESCRIPTIONS[5];
                this.imageEventText.updateBodyText(descriptionCalc);
                this.imageEventText.clearAllDialogs();
                this.imageEventText.updateDialogOption(0, OPTIONS[4]);

                screenNum = 3;
                break;
            default:
                openMap();
        }
    }

    public void update() {
        super.update();
        if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            CardCrawlGame.sound.play("CARD_EXHAUST");
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0), (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));
            AbstractDungeon.player.masterDeck.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

    }
}
