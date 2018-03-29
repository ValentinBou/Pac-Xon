package imt.uvinfo.pacxon.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import imt.uvinfo.pacxon.modele.Heros;
import imt.uvinfo.pacxon.modele.Jeu;
import imt.uvinfo.pacxon.modele.Niveau;
import imt.uvinfo.pacxon.modele.TypeBloc;

public class PacXon implements ApplicationListener {
    private Viewport viewport;
    private SpriteBatch sprites;
    private ShapeRenderer shapes;
    private float elapsed;
    
    private BitmapFont policeNormale;
    
    private TextureAtlas textures;

    boolean pause = false;
    
    // Taille fen�tre
    int largeurFenetre = 800;
    int hauteurFenetre = 600;
    // Acc�s au modele
    Jeu monJeu;
    // Garde en m�moire le sprite du h�ros, � afficher lorsque celui-ci ne bouge plus
    String dernierSpriteHeros;

    @Override
    public void create() {
        // le viewport g�re la cam�ra quand la fen�tre change de taille
        viewport = new ScalingViewport(Scaling.fit, largeurFenetre, hauteurFenetre);

        // prise en compte de la transparence des couleurs
        Gdx.gl.glEnable(GL30.GL_BLEND);

        // rendu en batches
        sprites = new SpriteBatch();
        shapes = new ShapeRenderer();
        policeNormale = new BitmapFont();
        policeNormale.setColor(Color.WHITE);
        
        /* Init jeu */
        monJeu = new Jeu(3);
        
        // Chargement des textures
        textures = new TextureAtlas(Gdx.files.internal("Textures_64px.atlas"));
    	
        // Init sauvegarde du dernier �tat du h�ros (regarde par d�faut vers la droite)
    	dernierSpriteHeros = "Textures_64px_heros_right";
    	
    }
    
    @Override
    public void render() {
    	
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) pause();
        
        // Temps pass� total
        elapsed += Gdx.graphics.getDeltaTime();
        
        // Coloration du fond de la fen�tre
        Gdx.gl.glClearColor(0, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        
        if(monJeu.isFini() || monJeu.isPerdu()) {
        	// Jeu fini
        	// TODO : Centrer le texte, et pourquoi pas créer sa police avec classe police, images pour chaque lettre, getstring etc...
        	sprites.begin();
        	if(monJeu.isFini()) {
        		policeNormale.draw(sprites, "You won!", largeurFenetre/2, hauteurFenetre/2);
        	} else if(monJeu.isPerdu()) {
        		policeNormale.draw(sprites, "You died :'(", largeurFenetre/2, hauteurFenetre/2);
        	}
            sprites.end();
        	
        } else {
        	// Affichage du jeu
        	
	        // R�cup�re le niveau
	        Niveau niveauActuel = monJeu.getNiveauActuel();
	        
	        // R�cup�rer le h�ros
	        Heros heros = monJeu.getNiveauActuel().getHeros();
	        
	        // Gestion des touches fl�ch�es (direction du heros)
	        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
	        	heros.setFlagArrowRight();
	        } else {
	        	heros.unSetFlagArrowRight();
	        }
	        
	        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
	        	heros.setFlagArrowUp();
	        } else {
	        	heros.unSetFlagArrowUp();
	        }
	        
	        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
	        	heros.setFlagArrowLeft();
	        } else {
	        	heros.unSetFlagArrowLeft();
	        }
	        
	        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
	        	heros.setFlagArrowDown();
	        } else {
	        	heros.unSetFlagArrowDown();
	        }
	        
	        int i = 0;
	        int j = 0;
	        int totalLargeur = niveauActuel.getTerrain().getLargeur();
	        int totalHauteur = niveauActuel.getTerrain().getHauteur();
	    	int unitLargeur = largeurFenetre/totalLargeur;
	        int unitHauteur = hauteurFenetre/totalHauteur;
	
	        shapes.begin(ShapeRenderer.ShapeType.Line);
	        //shapes.setColor(0, 0.5f, 1, 0.3f);
	        //shapes.rect(10, 10, 780, 580);
	        // Pas de formes � cr�er
	        shapes.end();
	
	        sprites.setProjectionMatrix(viewport.getCamera().combined);
	        sprites.begin();
	        TypeBloc tmpBloc = null;
	        // Pour chaque bloc du terrain
	        for(i = 0; i < totalLargeur; i++) {
	        	for(j = 0; j < totalHauteur; j++) {
	        		// R�cup�ration du type du bloc
	            	tmpBloc = niveauActuel.getTerrain().getBloc(i, j);
	            	// Affichage de l'image du bloc selon son type
	        		if(tmpBloc == TypeBloc.Bordure) {
	        			sprites.draw(textures.findRegion("Textures_64px_bordure"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
	        		} else if(tmpBloc == TypeBloc.BlocNormal) {
	        			sprites.draw(textures.findRegion("Textures_64px_blocnormal"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
	        		} else if(tmpBloc == TypeBloc.Vide){
	        			sprites.draw(textures.findRegion("Textures_64px_vide"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
	        		} else if(tmpBloc == TypeBloc.Trace){
	        			sprites.draw(textures.findRegion("Textures_64px_trace"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
	        		} else if(tmpBloc == TypeBloc.TraceTouche){
	        			sprites.draw(textures.findRegion("Textures_64px_tracetouche"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
	        		} else {
	        			// Bloc inconnu
	        			sprites.draw(textures.findRegion("Textures_64px_none"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
	        		}	        		
	        	}
	        }
	        
	        // Affichage du h�ros selon sa direction
	        if(heros.getDirectionY() > 0.0) {
	        	sprites.draw(textures.findRegion("Textures_64px_heros_up"), (int)(heros.getPosX()*largeurFenetre), (int)(heros.getPosY()*hauteurFenetre), unitLargeur, unitHauteur);
	        	dernierSpriteHeros = "Textures_64px_heros_up";
	        } else if(heros.getDirectionY() < 0.0) {
	        	sprites.draw(textures.findRegion("Textures_64px_heros_down"), (int)(heros.getPosX()*largeurFenetre), (int)(heros.getPosY()*hauteurFenetre), unitLargeur, unitHauteur);
	        	dernierSpriteHeros = "Textures_64px_heros_down";
	        } else if(heros.getDirectionX() > 0.0) {
	        	sprites.draw(textures.findRegion("Textures_64px_heros_right"), (int)(heros.getPosX()*largeurFenetre), (int)(heros.getPosY()*hauteurFenetre), unitLargeur, unitHauteur);
	        	dernierSpriteHeros = "Textures_64px_heros_right";
	        } else if(heros.getDirectionX() < 0.0) {
	        	sprites.draw(textures.findRegion("Textures_64px_heros_left"), (int)(heros.getPosX()*largeurFenetre), (int)(heros.getPosY()*hauteurFenetre), unitLargeur, unitHauteur);
	        	dernierSpriteHeros = "Textures_64px_heros_left";
	        } else {
	        	// Le h�ros ne bouge pas, on continue d'afficher selon le sens pr�c�dent
	        	sprites.draw(textures.findRegion(dernierSpriteHeros), (int)(heros.getPosX()*largeurFenetre), (int)(heros.getPosY()*hauteurFenetre), unitLargeur, unitHauteur);
	        }
	        
	        // Affichage de tous les monstres selon leur direction
	        for(i = 0; i < niveauActuel.getNbMonstres(); i++) {
	        	if(niveauActuel.getMonstre(i).getDirectionX() < 0.0) {
	            	sprites.draw(textures.findRegion("Textures_64px_"+niveauActuel.getMonstre(i).iconeName+"_left"), (int)(niveauActuel.getMonstre(i).getPosX()*largeurFenetre), (int)(niveauActuel.getMonstre(i).getPosY()*hauteurFenetre), niveauActuel.getMonstre(i).getLargeur()*unitLargeur, niveauActuel.getMonstre(i).getHauteur()*unitHauteur);
	        	} else {
	            	sprites.draw(textures.findRegion("Textures_64px_"+niveauActuel.getMonstre(i).iconeName+"_right"), (int)(niveauActuel.getMonstre(i).getPosX()*largeurFenetre), (int)(niveauActuel.getMonstre(i).getPosY()*hauteurFenetre), niveauActuel.getMonstre(i).getLargeur()*unitLargeur, niveauActuel.getMonstre(i).getHauteur()*unitHauteur);
	        	}
	        }
	
	        sprites.end();
	        
	        /* On met � jour le niveau, la m�j s'affichera � la frame suivante */
	        /* La mise � jour ne se fait pas si le temps est trop grand, pour �viter des erreurs (ici 0.5 seconde : 2fps minimum) */
	        /* TODO : Possibilit� de cr�er un thread qui mettra � jour lui-m�me l'affichage, pour �viter des probl�mes lorsqu'il n'y a pas de render */
	        if(pause == false) { 
	        	if(Gdx.graphics.getDeltaTime() <= 0.5) {
	        		monJeu.update(Gdx.graphics.getDeltaTime());
	        	}
	        }
        }
        
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    	if( pause == true ) {
    		System.out.println("Replay");
    		pause = false;
    	} else {
    		System.out.println("Pause");
    		pause = true;
    	}
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
