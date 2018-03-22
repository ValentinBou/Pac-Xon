package imt.uvinfo.pacxon.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import imt.uvinfo.pacxon.modele.Jeu;
import imt.uvinfo.pacxon.modele.Niveau;
import imt.uvinfo.pacxon.modele.TypeBloc;

public class PacXon implements ApplicationListener {
    private Viewport viewport;
    private SpriteBatch sprites;
    private ShapeRenderer shapes;
    private float elapsed;
    
    
    private Animation<TextureRegion> idle, walk;
    private TextureAtlas textures;
    
    /* MON CODE */
    Jeu monJeu;
    private Texture vide;
    private Texture bordure;
    private Texture heros;
    private Texture monstre;

    @Override
    public void create() {
        // le viewport gère la caméra quand la fenêtre change de taille
        viewport = new ScalingViewport(Scaling.fit, 800, 600);

        // prise en compte de la transparence des couleurs
        Gdx.gl.glEnable(GL30.GL_BLEND);

        // rendu en batches
        sprites = new SpriteBatch();
        shapes = new ShapeRenderer();
        
        /* MON CODE */
        monJeu = new Jeu(3);
        
        
        textures = new TextureAtlas(Gdx.files.internal("blocsUnicolor.atlas"));
    	
    	

    	/*
        // le viewport gère la caméra quand la fenêtre change de taille
        viewport = new ScalingViewport(Scaling.fit, 800, 600);

        // prise en compte de la transparence des couleurs
        Gdx.gl.glEnable(GL30.GL_BLEND);

        // rendu en batches
        sprites = new SpriteBatch();
        shapes = new ShapeRenderer();

        textures = new TextureAtlas(Gdx.files.internal("alienGreen.atlas"));
        walk = new Animation<>(0.25f,
                               textures.findRegion("alienGreen_walk1"),
                               textures.findRegion("alienGreen_walk2"));
        idle = new Animation<>(2f, new Array<>(new TextureRegion[]{
                textures.findRegion("alienGreen"),
                textures.findRegion("alienGreen_stand")
        }), Animation.PlayMode.LOOP_RANDOM);
        
        
        
        */
    }

    @Override
    public void render() {
    	
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

        elapsed += Gdx.graphics.getDeltaTime();
        
        Niveau niveauActuel = monJeu.getNiveauActuel();
        
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        	niveauActuel.getHeros().setFlagArrowRight();
        } else {
        	niveauActuel.getHeros().unSetFlagArrowRight();
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
        	niveauActuel.getHeros().setFlagArrowUp();
        } else {
        	niveauActuel.getHeros().unSetFlagArrowUp();
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        	niveauActuel.getHeros().setFlagArrowLeft();
        } else {
        	niveauActuel.getHeros().unSetFlagArrowLeft();
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
        	niveauActuel.getHeros().setFlagArrowDown();
        } else {
        	niveauActuel.getHeros().unSetFlagArrowDown();
        }

        int i = 0;
        int j = 0;
        int totalLargeur = niveauActuel.getTerrain().getLargeur();
        int totalHauteur = niveauActuel.getTerrain().getHauteur();
    	int unitLargeur = 800/totalLargeur;
        int unitHauteur = 600/totalHauteur;
        
        Gdx.gl.glClearColor(0, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        shapes.begin(ShapeRenderer.ShapeType.Line);
        //shapes.setColor(0, 0.5f, 1, 0.3f);
        //shapes.rect(10, 10, 780, 580);
        shapes.end();

        sprites.setProjectionMatrix(viewport.getCamera().combined);
        sprites.begin();
        for(i = 0; i < totalLargeur; i++) {
        	for(j = 0; j < totalHauteur; j++) {
        		if(niveauActuel.getTerrain().getBloc(i, j) == TypeBloc.Bordure) {
        			sprites.draw(textures.findRegion("blocsUnicolor_bordure"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		} else {
        			sprites.draw(textures.findRegion("blocsUnicolor_vide"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		}
        		
        	}
        	
        }
        sprites.draw(textures.findRegion("blocsUnicolor_heros"), (int)(niveauActuel.getHeros().getPosX()*800), (int)(niveauActuel.getHeros().getPosY()*600), unitLargeur, unitHauteur);
        
        for(i = 0; i < niveauActuel.getNbPersonnages(); i++) {
        	sprites.draw(textures.findRegion("blocsUnicolor_monstre"), (int)(niveauActuel.getPersonnage(i).getPosX()*800), (int)(niveauActuel.getPersonnage(i).getPosY()*600), unitLargeur, unitHauteur);
        }
        
        //sprites.draw(textures.findRegion("blocsUnicolor_bordure"), 0, 0, unitLargeur, unitHauteur);
        sprites.end();
        
    	//draw(Texture texture, float x, float y, float width, float height)
    	//Draws a rectangle with the bottom left corner at x,y and stretching the region to cover the given width and height.
        
        /* On pet � jour le niveau, la m�j s'affichera � la frame suivante */
        niveauActuel.update(Gdx.graphics.getDeltaTime());
        
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
